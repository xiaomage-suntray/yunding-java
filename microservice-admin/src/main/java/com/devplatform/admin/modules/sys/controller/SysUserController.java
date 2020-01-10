
package com.devplatform.admin.modules.sys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.modules.sys.bean.SysUserOrg;
import com.devplatform.admin.modules.sys.bean.SysUserPost;
import com.devplatform.admin.modules.sys.bean.SysUserRoleEntity;
import com.devplatform.admin.modules.sys.form.PasswordForm;
import com.devplatform.admin.modules.sys.service.SysOrgService;
import com.devplatform.admin.modules.sys.service.SysUserOrgService;
import com.devplatform.admin.modules.sys.service.SysUserPostService;
import com.devplatform.admin.modules.sys.service.SysUserRoleService;
import com.devplatform.admin.modules.sys.service.SysUserService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.Assert;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.MapUtils;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@Api(tags = { "系统用户接口" })
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
    
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SysUserRoleService sysUserRoleService;
    
    @Autowired
    private SysUserOrgService sysUserOrgService;
    
    @Autowired
    private SysUserPostService sysUserPostService;
    
    @Autowired
    private SysOrgService sysOrgService;
    
    /**
     * 所有用户列表
     */
    @ApiOperation(value = "根据条件获取分页数据列表", notes = "根据条件获取分页数据列表")
    @ApiImplicitParam(name = "params", value = "查询参数", required = true, paramType = "query", dataType = "Map<String, Object>")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        //		if(!getUserId().equals(Constant.SUPER_ADMIN)){
        //			params.put("createUserId", getUserId());
        //		}
        String username = (String) params.get("username");
        String createUserId = (String) params.get("createUserId");
        String type = (String) params.get("type");
        String isExperts = (String) params.get("isExperts");
        PageUtils page = sysUserService.queryPage(params,
                new QueryWrapper<SysUserEntity>().like(StringUtil.checkNotNull(username), "username", username)
                        .eq(createUserId != null, "create_user_id", createUserId).eq(type != null, "type", type)
                        .eq(isExperts != null, "is_experts", isExperts));
        System.out.println("打印登录之后角色ID=============" + getUser().toString());
        //TODO: 展示每一个用户拥有哪些角色，有哪些岗位，隶属于哪些组织机构
        return R.ok().put("page", page);
    }
    
    /**
     * 获取机构下所有人员
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月13日上午11:09:07
     * @param params
     * @return
     */
    @ApiOperation(value = "获取机构下所有人员", notes = "获取机构下所有人员")
    @ApiImplicitParam(name = "params", value = "查询参数", required = true, paramType = "query", dataType = "Map<String, Object>")
    @GetMapping("/listByOrgCode")
    public R listByOrgCode(@RequestParam Map<String, Object> params) {
        String orgCode = (String) params.get("orgCode");
        SysUserEntity bean = new SysUserEntity();
        bean.setOrgCode(orgCode);
        List<SysUserEntity> list = sysUserService.queryByOrgCode(bean);
        return R.ok().put("list", list);
    }
    
    /**
     * 获取登录的用户信息
     */
    @ApiOperation(value = "获取登录的用户信息", notes = "获取登录的用户信息")
    @GetMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }
    
    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ApiImplicitParam(name = "form", value = "密码表单实体", required = true, paramType = "query", dataType = "PasswordForm")
    @PostMapping("/password")
    public R password(@RequestBody PasswordForm form) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");
        
        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
        
        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return R.error("原密码不正确");
        }
        
        return R.ok();
    }
    
    /**
     * 用户信息
     */
    @ApiOperation(value = "根据ID获取用户信息", notes = "根据ID获取用户信息")
    @ApiImplicitParam(name = "userId", value = "主键", required = true, paramType = "query", dataType = "String")
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") String userId) {
        SysUserEntity user = sysUserService.getById(userId);
        //获取用户所属的角色列表
        List<String> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        //缓存用户的组织机构信息
        List<SysUserOrg> userOrgList = sysUserOrgService.list(new QueryWrapper<SysUserOrg>().eq("user_id", user.getUserId()));
        List<String> orgIdList = new ArrayList<String>();
        for (SysUserOrg userOrg : userOrgList) {
            orgIdList.add(userOrg.getOrgId());
        }
        user.setOrgIdList(orgIdList);
        //缓存用户的岗位信息
        List<SysUserPost> userPostList = sysUserPostService.list(new QueryWrapper<SysUserPost>().eq("user_id", user.getUserId()));
        List<String> postIdList = new ArrayList<String>();
        for (SysUserPost userPost : userPostList) {
            postIdList.add(userPost.getPostId());
        }
        user.setPostIdList(postIdList);
        return R.ok().put("user", user);
    }
    
    /**
     * 获取当前登陆者单位和部门
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月7日上午9:22:55
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取当前登陆者单位和部门", notes = "获取当前登陆者单位和部门")
    @GetMapping("/infoOrgAndCompany")
    public R infoOrgAndCompany() {
        SysUserEntity user = getUser();
        user = sysUserService.infoOrgAndCompany(user);
        return R.ok().put("user", user);
    }
    
    /**
     * 获取当前登陆者角色
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月7日上午9:35:12
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取当前登陆者角色", notes = "获取当前登陆者角色")
    @GetMapping("/infoRole")
    public R infoRole() {
        SysUserEntity user = getUser();
        //获取用户所属的角色列表
        List<String> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
        user.setRoleIdList(roleIdList);
        return R.ok().put("user", user);
    }
    
    /**
     * 通過用戶名獲取用户信息
     */
    @ApiOperation(value = "根据用户信息获取用户信息", notes = "根据用户信息获取用户信息")
    @ApiImplicitParam(name = "userName", value = "主键", required = true, paramType = "query", dataType = "String")
    @GetMapping("/infoByName/{userName}")
    public SysUserEntity infoByName(@PathVariable("userName") String userName) {
        SysUserEntity user = sysUserService.queryByUserName(userName);
        
        //获取用户所属的角色列表
        List<String> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
        user.setRoleIdList(roleIdList);
        
        return user;
    }
    
    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @ApiOperation(value = "保存用户", notes = "保存用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, paramType = "query", dataType = "SysUserEntity")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user);
        
        user.setCreateUserId(getUserId());
        user.setType("1");
        sysUserService.save(user);
        
        return R.ok();
    }
    
    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @PostMapping("/update")
    public R update(@RequestBody @ApiParam(value = "用户实体", required = true) SysUserEntity user) {
        //        ValidatorUtils.validateEntity(user);
        user.setCreateUserId(getUserId());
        sysUserService.update(user);
        return R.ok();
    }
    
    /**
     * 设置为专家和删除专家
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月21日下午6:08:23
     * @param user
     * @return
     */
    @SysLog("修改用户-设置为专家和删除专家")
    @ApiOperation(value = "修改用户-设置为专家和删除专家", notes = "修改用户-设置为专家和删除专家用户")
    @PostMapping("/updateIsExperts")
    public R updateIsExperts(@RequestBody @ApiParam(value = "用户实体", required = true) SysUserEntity user) {
        //添加  专家角色
        String roolId = "3cf531a02e634311b8f0043231597f3d";
        if (user.getIsExperts() == 2) {
            SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
            sysUserRoleEntity.setUserId(user.getUserId());
            sysUserRoleEntity.setRoleId(roolId);
            sysUserRoleService.save(sysUserRoleEntity);
        }
        else {
            //删除  专家角色
            sysUserRoleService.removeByMap(new MapUtils().put("user_id", user.getUserId()).put("role_id", roolId));
        }
        sysUserService.updateById(user);
        return R.ok();
    }
    
    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "userIds", value = "用户主键(多个用','隔开)", required = true, paramType = "query", dataType = "String")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody String[] userIds) {
        if (ArrayUtils.contains(userIds, "1")) {
            return R.error("系统管理员不能删除");
        }
        
        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }
        
        sysUserService.deleteBatch(userIds);
        
        return R.ok();
    }
    
    /**
     * 根据角色id获取用户列表
     */
    @ApiOperation(value = "根据角色id获取用户列表", notes = "根据角色id获取用户列表")
    @GetMapping("/queryUserListByRoleId")
    public R queryUserListByRoleId(@RequestParam Map<String, Object> params) {
        String roleId = (String) params.get("roleId");
        List<SysUserEntity> list = sysUserService.queryUserListByRoleId(roleId);
        return R.ok().put("list", list);
    }
    
    /**
     * 根据用户类别获取所有用户
     */
    @ApiOperation(value = "根据用户类别获取所有用户", notes = "根据用户类别获取所有用户")
    @GetMapping("/listByType/{type}")
    public R listByType(@PathVariable("type") @ApiParam(value = "用户类别", required = true) String type) {
        List<SysUserEntity> list = sysUserService.list(new QueryWrapper<SysUserEntity>().eq("type", type));
        return R.ok().put("list", list);
    }
}
