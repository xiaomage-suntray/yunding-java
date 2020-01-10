package com.devplatform.admin.modules.sys.service;


import java.util.List;
import java.util.Map;

import com.devplatform.admin.modules.sys.bean.SysMenuEntity;
import com.devplatform.admin.modules.sys.model.SysMenuModel;
import com.devplatform.common.service.MyBaseService;
import com.devplatform.common.util.PageUtils;


/**
 * 菜单管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:16
 */
public interface SysMenuService extends MyBaseService<SysMenuEntity> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(String parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenuModel> getUserMenuList(String userId);

	/**
	 * 删除
	 */
	void delete(String menuId);
}
