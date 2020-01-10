package com.devplatform.admin.business.sysArea.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="区域表对象",description="区域表对象")//swagger注解
@TableName("sys_area")//mybatisplus注解
public class SysArea implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//主键id
	@ApiModelProperty(value="创建人",name="createdUserId")
	private java.lang.String createdUserId;//创建人
	@ApiModelProperty(value="创建时间",name="createdTime")
	private java.util.Date createdTime;//创建时间
	@ApiModelProperty(value="更新人",name="updatedUserId")
	private java.lang.String updatedUserId;//更新人
	@ApiModelProperty(value="更新时间",name="updatedTime")
	private java.util.Date updatedTime;//更新时间
	@ApiModelProperty(value="",name="name")
	private java.lang.String name;//
	@ApiModelProperty(value="",name="parentId")
	private java.lang.String parentId;//

	public List<SysArea> getChildren() {
		return children;
	}

	public void setChildren(List<SysArea> children) {
		this.children = children;
	}

	private List<SysArea> children;

	/**
	 * 主键id的getter方法
	 */
	public java.lang.String getId(){
		return id;
	}
	/**
	 * 主键id的setter方法
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 * 创建人的getter方法
	 */
	public java.lang.String getCreatedUserId(){
		return createdUserId;
	}
	/**
	 * 创建人的setter方法
	 */
	public void setCreatedUserId(java.lang.String createdUserId){
		this.createdUserId = createdUserId;
	}
	/**
	 * 创建时间的getter方法
	 */
	public java.util.Date getCreatedTime(){
		return createdTime;
	}
	/**
	 * 创建时间的setter方法
	 */
	public void setCreatedTime(java.util.Date createdTime){
		this.createdTime = createdTime;
	}
	/**
	 * 更新人的getter方法
	 */
	public java.lang.String getUpdatedUserId(){
		return updatedUserId;
	}
	/**
	 * 更新人的setter方法
	 */
	public void setUpdatedUserId(java.lang.String updatedUserId){
		this.updatedUserId = updatedUserId;
	}
	/**
	 * 更新时间的getter方法
	 */
	public java.util.Date getUpdatedTime(){
		return updatedTime;
	}
	/**
	 * 更新时间的setter方法
	 */
	public void setUpdatedTime(java.util.Date updatedTime){
		this.updatedTime = updatedTime;
	}
	/**
	 * 的getter方法
	 */
	public java.lang.String getName(){
		return name;
	}
	/**
	 * 的setter方法
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 * 的getter方法
	 */
	public java.lang.String getParentId(){
		return parentId;
	}
	/**
	 * 的setter方法
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
}
