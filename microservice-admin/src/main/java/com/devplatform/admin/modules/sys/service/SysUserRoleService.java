package com.devplatform.admin.modules.sys.service;

import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysUserRoleEntity;
import com.devplatform.common.service.MyBaseService;



/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:43:24
 */
public interface SysUserRoleService extends MyBaseService<SysUserRoleEntity> {
	
	void saveOrUpdate(String userId, List<String> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<String> queryRoleIdList(String userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
