package com.devplatform.admin.modules.sys.service;

import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysRoleMenuEntity;
import com.devplatform.common.service.MyBaseService;



/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:30
 */
public interface SysRoleMenuService extends MyBaseService	<SysRoleMenuEntity> {
	
	void saveOrUpdate(String roleId, List<String> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
	
}
