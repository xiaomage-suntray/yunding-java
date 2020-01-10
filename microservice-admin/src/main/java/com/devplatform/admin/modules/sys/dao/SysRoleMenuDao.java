package com.devplatform.admin.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.devplatform.admin.modules.sys.bean.SysRoleMenuEntity;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:33:46
 */
@Mapper
public interface SysRoleMenuDao extends MyBaseMapper<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(String[] roleIds);
}
