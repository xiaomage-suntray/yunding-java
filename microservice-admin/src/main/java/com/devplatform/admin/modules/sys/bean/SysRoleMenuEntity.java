package com.devplatform.admin.modules.sys.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:13
 */
@ApiModel(value="角色与菜单对应关系对象",description="角色与菜单对应关系")
@TableName("sys_role_menu")
public class SysRoleMenuEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7664680780986079950L;

	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private String id;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value="角色ID",name="roleId")
	private String roleId;

	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value="菜单ID",name="menuId")
	private String menuId;

	/**
	 * 设置：
	 * @param id 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取：
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 设置：角色ID
	 * @param roleId 角色ID
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取：角色ID
	 * @return String
	 */
	public String getRoleId() {
		return roleId;
	}
	
	/**
	 * 设置：菜单ID
	 * @param menuId 菜单ID
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获取：菜单ID
	 * @return String
	 */
	public String getMenuId() {
		return menuId;
	}
	
}
