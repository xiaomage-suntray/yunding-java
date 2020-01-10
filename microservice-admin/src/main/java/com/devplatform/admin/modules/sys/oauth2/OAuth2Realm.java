
package com.devplatform.admin.modules.sys.oauth2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.common.utils.JwtUtil;
import com.devplatform.admin.modules.sys.bean.SysDatapermission;
import com.devplatform.admin.modules.sys.bean.SysOrg;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.modules.sys.bean.SysUserOrg;
import com.devplatform.admin.modules.sys.service.ShiroService;
import com.devplatform.admin.modules.sys.service.SysDatapermissionService;
import com.devplatform.admin.modules.sys.service.SysOrgService;
import com.devplatform.admin.modules.sys.service.SysUserOrgService;
import com.devplatform.admin.modules.sys.service.SysUserService;
import com.devplatform.common.base.exception.RRException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * 认证
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-20 14:00
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    
    @Autowired
    private ShiroService shiroService;
    
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SysDatapermissionService sysDatapermissionService;
    
    @Autowired
    private SysUserOrgService sysUserOrgService;
    
    @Autowired
    private SysOrgService sysOrgService;
    
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }
    
    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity) principals.getPrimaryPrincipal();
        String userId = user.getUserId();
        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }
    
    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        
        SysUserEntity user = new SysUserEntity();
        if (accessToken == null) {
            throw new RRException("缺少或无效的token");
        }
        final String JwtToken = accessToken;//.substring(7);
        try {
            final Claims claims = JwtUtil.parseJWT(JwtToken);
            String subject = claims.getSubject();
            JSONObject json = JSON.parseObject(subject);
            String userId = json.getString("userId");
            //            SysUserEntity userInfo = sysUserService.getById(userId);
            user = sysUserService.getById(userId);
            //            user.setUserId(userId);
            //            user.setUsername(userInfo.getUsername());
            //            user.setThename(userInfo.getThename());
            //  hanleUserInfo(user);
        }
        catch (final SignatureException e) {
            throw new RRException("token解析异常");
        }
        catch (final ExpiredJwtException e) {
            throw new RRException("token过期，请重新登陆");
        }
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
    
    private void hanleUserInfo(SysUserEntity user) {
        //缓存用户的组织机构信息
        List<SysUserOrg> userOrgList = sysUserOrgService.list(new QueryWrapper<SysUserOrg>().eq("user_id", user.getUserId()));
        List<String> orgIdList = new ArrayList<String>();
        List<String> orgNameList = new ArrayList<String>();
        
        /**
         *  由于存在一个用户有多个组织机构的可能性，故需要记录本次用户登录所选择的组织机构
         *  目前先随便取一个所属的组织机构，
         *  等到后面前后台都加上选择所属组织机构的时候，在将此处的当前选择组织机构信息迁移
         */
        String currentOrgId = "";
        String currentOrgCode = "";
        
        for (SysUserOrg userOrg : userOrgList) {
            currentOrgId = userOrg.getOrgId();
            currentOrgCode = userOrg.getOrgCode();
            
            //获取当前用户所在公司
            if (null != userOrg.getOrgCode() && !userOrg.getOrgCode().equals("")) {
                SysOrg org = sysOrgService.getOne(new QueryWrapper<SysOrg>().eq("org_code", userOrg.getOrgCode().split("_")[0]));
                user.setOrgName(org.getOrgName());
                user.setOrgCode(org.getOrgCode());
                SysOrg orgd = sysOrgService.getOne(new QueryWrapper<SysOrg>().eq("org_code", userOrg.getOrgCode()));
                orgNameList.add(orgd.getOrgName());
            }
            orgIdList.add(userOrg.getOrgId() + "#" + userOrg.getOrgCode());
            
        }
        user.setOrgIdList(orgIdList);
        user.setOrgNameList(orgNameList);
        user.setCurrentChoseOrgId(currentOrgId);
        user.setCurrentChoseOrgCode(currentOrgCode);
        
        List<SysDatapermission> dataPermissionList = sysDatapermissionService.queryListByUserId(user.getUserId());
        
        List<String> dataPermissionArray = new ArrayList<String>();
        for (SysDatapermission obj : dataPermissionList) {
            if (!dataPermissionArray.contains(obj.getAddparam())) {
                dataPermissionArray.add(obj.getAddparam());
            }
        }
        user.setDataPermissionStr(dataPermissionArray);
        
    }
}
