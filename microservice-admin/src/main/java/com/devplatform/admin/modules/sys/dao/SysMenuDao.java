package com.devplatform.admin.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.devplatform.admin.modules.sys.bean.SysMenuEntity;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * 菜单管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:33:01
 */
@Mapper
public interface SysMenuDao extends MyBaseMapper<SysMenuEntity> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(String parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();

}
