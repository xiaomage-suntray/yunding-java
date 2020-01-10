package com.devplatform.admin.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.devplatform.admin.modules.sys.bean.SysUserRoleEntity;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:46
 */
@Mapper
public interface SysUserRoleDao extends MyBaseMapper<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<String> queryRoleIdList(String userId);


	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
	/**
	 * 保存用户角色
	 */
	void saveSysUserRole(SysUserRoleEntity sysUserRole);
}
