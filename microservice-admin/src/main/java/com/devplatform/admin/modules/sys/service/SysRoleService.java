package com.devplatform.admin.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.devplatform.admin.modules.sys.bean.SysRoleEntity;
import com.devplatform.admin.modules.sys.bean.SysUserRoleEntity;
import com.devplatform.common.service.MyBaseService;
import com.devplatform.common.util.PageUtils;


/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService extends MyBaseService<SysRoleEntity> {

	/**
	 * 保存用户
	 */
	boolean saveMenu(SysRoleEntity role);

	void update(SysRoleEntity role);

	void deleteBatch(String[] roleIds);

	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<String> queryRoleIdList(String createUserId);
	/**
	 * TODO 查询当前登陆人的角色列表  id  name  供应商评分角色使用
	 *
	 * @return List<SysRoleEntity>
	 * @Param  sysUserRoleEntity
	 * @Author heibin
	 * @Date 2019/4/9 13:49
	 **/
	List<SysRoleEntity> sysRoleList(SysUserRoleEntity sysUserRoleEntity);
}
