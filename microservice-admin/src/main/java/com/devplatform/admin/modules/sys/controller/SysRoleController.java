
package com.devplatform.admin.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devplatform.admin.modules.sys.bean.SysUserRoleEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.devplatform.admin.modules.sys.bean.SysRoleEntity;
import com.devplatform.admin.modules.sys.service.SysRoleMenuService;
import com.devplatform.admin.modules.sys.service.SysRoleService;
import com.devplatform.common.base.annotation.SysLog;
import com.devplatform.common.base.validator.ValidatorUtils;
import com.devplatform.common.util.Constant;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.R;
import com.devplatform.common.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 角色管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月8日 下午2:18:33
 */
@Api(tags = { "角色管理接口" })
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    
    private static String CREATE_USER_ID = "create_user_id";
    
    @Autowired
    private SysRoleService sysRoleService;
    
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    
    /**
     * 角色列表
     */
    @ApiOperation(value = "根据条件获取分页数据列表", notes = "根据条件获取分页数据列表")
    @ApiImplicitParam(name = "params", value = "查询参数", required = true, paramType = "query", dataType = "Map<String, Object>")
    @GetMapping("/list")
    //	@RequiresPermissions("sys:role:list")
    public R list(@RequestParam Map<String, Object> params) {
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put(CREATE_USER_ID, getUserId());
        }
        String roleName = (String) params.get("roleName");
        String createUserId = (String) params.get("createUserId");
        PageUtils page = sysRoleService.queryPage(params, new QueryWrapper<SysRoleEntity>()
                .like(StringUtil.checkNotNull(roleName), "role_name", roleName).eq(createUserId != null, CREATE_USER_ID, createUserId));
        
        return R.ok().put("page", page);
    }
    
    /**
     * 角色列表
     */
    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @GetMapping("/select")
    //	@RequiresPermissions("sys:role:select")
    public R select() {
        Map<String, Object> map = new HashMap<>();
        
        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if (!getUserId().equals(Constant.SUPER_ADMIN)) {
            map.put(CREATE_USER_ID, getUserId());
        }
        List<SysRoleEntity> list = (List<SysRoleEntity>) sysRoleService.listByMap(map);
        
        return R.ok().put("list", list);
    }
    
    /**
     * 角色信息
     */
    @ApiOperation(value = "根据ID获取角色信息", notes = "根据ID获取角色信息")
    @ApiImplicitParam(name = "roleId", value = "主键", required = true, paramType = "query", dataType = "String")
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("roleId") String roleId) {
        SysRoleEntity role = sysRoleService.getById(roleId);
        
        //查询角色对应的菜单
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        
        return R.ok().put("role", role);
    }
    
    /**
     * 保存角色
     */
    @SysLog("保存角色")
    @ApiOperation(value = "保存角色", notes = "保存角色")
    @ApiImplicitParam(name = "role", value = "角色实体类", required = true, paramType = "query", dataType = "SysRoleEntity")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public R save(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);
        
        role.setCreateUserId(getUserId());
        sysRoleService.saveMenu(role);
        
        return R.ok();
    }
    
    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @ApiOperation(value = "修改角色", notes = "修改角色")
    @ApiImplicitParam(name = "role", value = "角色实体类", required = true, paramType = "query", dataType = "SysRoleEntity")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);
        
        role.setCreateUserId(getUserId());
        sysRoleService.update(role);
        
        return R.ok();
    }
    
    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParam(name = "roleIds", value = "角色主键(多个用','隔开)", required = true, paramType = "query", dataType = "String")
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public R delete(@RequestBody String[] roleIds) {
        sysRoleService.deleteBatch(roleIds);
        return R.ok();
    }
    /**
     * TODO 查询当前登陆人的角色列表  id  name  供应商评分角色使用
     *
     * @return List<SysRoleEntity>
     * @Param
     * @Author heibin
     * @Date 2019/4/9 13:49
     **/
    @ApiOperation(value="获取角色列表", notes="获取角色列表")
    @GetMapping("/sysRoleList")
    public R sysRoleList(){
        SysUserRoleEntity sysUserRoleEntity =new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(getUserId());
        List<SysRoleEntity> list =sysRoleService.sysRoleList(sysUserRoleEntity);

        return R.ok().put("list", list);
    }
    
}
