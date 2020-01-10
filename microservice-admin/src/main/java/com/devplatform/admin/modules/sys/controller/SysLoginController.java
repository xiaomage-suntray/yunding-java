
package com.devplatform.admin.modules.sys.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.admin.common.utils.JwtUtil;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.modules.sys.bean.SysUserRoleEntity;
import com.devplatform.admin.modules.sys.form.SysLoginForm;
import com.devplatform.admin.modules.sys.service.ShiroService;
import com.devplatform.admin.modules.sys.service.SysCaptchaService;
import com.devplatform.admin.modules.sys.service.SysUserRoleService;
import com.devplatform.admin.modules.sys.service.SysUserService;
import com.devplatform.admin.modules.sys.service.SysUserTokenService;
import com.devplatform.common.base.exception.RRException;
import com.devplatform.common.util.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Decoder;

/**
 * 登录相关
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@Api(tags = { "登陆接口" })
@RestController
public class SysLoginController extends AbstractController {
    
    private final static Logger log = LoggerFactory.getLogger(SysLoginController.class);
    
    @Value("${environment}")
    private String environment;
    
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SysUserRoleService sysUserRoleService;
    
    @Autowired
    private SysUserTokenService sysUserTokenService;
    
    @Autowired
    private SysCaptchaService sysCaptchaService;
    
    @Autowired
    private ShiroService shiroService;
    
    /**
     * 验证码
     */
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @ApiImplicitParam(name = "uuid", value = "uuid", required = true, paramType = "query", dataType = "String")
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        
        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
    
    /**
     * 登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParam(name = "form", value = "用户实体类", required = true, paramType = "query", dataType = "SysLoginForm")
    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody SysLoginForm form) {
        
        //        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        //        if (!captcha) {
        //            return R.error("验证码不正确");
        //        }
        
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
        
        //账号不存在、密码错误 
        if (user == null) {
            return R.error("账号输入错误或者不存在");
        }
        
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }
        
        //账号锁定
        if (null != user.getStatus() && user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }
        
        //		Jwts.builder().setSubject(form.getUsername()).claim("roles", "member")
        //				.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        
        List<SysUserRoleEntity> userRoleList = sysUserRoleService.list(new QueryWrapper<SysUserRoleEntity>().eq("user_id", user.getUserId()));
        List<String> roleIdList = new ArrayList<String>();
        for (SysUserRoleEntity userRole : userRoleList) {
            roleIdList.add(userRole.getRoleId());
        }
        user.setRoleIdList(roleIdList);
        String jwtToken = null;
        try {
            jwtToken = JwtUtil.createJWT("longingJWT", JwtUtil.generalSubject(user), 1000 * 60 * 60 * 24 * 7);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RRException("JWT生成异常");
        }
        //用户权限列表
        Set<String> permissions = shiroService.getUserPermissions(user.getUserId());
        return R.ok().put("token", jwtToken).put("permissions", permissions).put("username", user.getUsername());
    }
    

    /**
     * 解密
    * @param s
    * @return
    */
    @SuppressWarnings("restriction")
    public static String decryptBASE64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = null;
                b = decoder.decodeBuffer(s);
                result = new String(b, "UTF-8");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 退出
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping("/sys/logout")
    public R logout() {
        sysUserTokenService.logout(getUserId());
        return R.ok();
    }
    
    public static void main(String[] args) {
        System.out.println(decryptBASE64("emhlbmdmYWNnLXpoaXl1YW4xMjMtMjAxOS0wNS0xMCAxNjoxMToyNg=="));
    }
    
}
