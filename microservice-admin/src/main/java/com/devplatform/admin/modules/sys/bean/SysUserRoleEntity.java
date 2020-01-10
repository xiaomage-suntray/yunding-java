package com.devplatform.admin.modules.sys.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户与角色对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:39
 */
@ApiModel(value="用户与角色对应关系对象",description="用户与角色对应关系")
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -23248503639080660L;

	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private String id;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value="用户ID",name="userId")
	private String userId;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value="角色ID",name="roleId")
	private String roleId;

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
	 * 设置：用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取：用户ID
	 * @return String
	 */
	public String getUserId() {
		return userId;
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
	
}
