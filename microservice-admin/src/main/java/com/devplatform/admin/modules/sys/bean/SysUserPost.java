package com.devplatform.admin.modules.sys.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="用户岗位关系对象",description="用户岗位关系对象")//swagger注解
@TableName("sys_user_post")//mybatisplus注解
public class SysUserPost implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id",name="userPostId")
	@TableId
	private java.lang.String userPostId;//id
	
	@ApiModelProperty(value="创建人ID",name="createUserId")
	private java.lang.String createUserId;//创建人ID
	@ApiModelProperty(value="创建时间",name="createTime")
	private java.util.Date createTime;//创建时间
	@ApiModelProperty(value="修改人ID",name="updateUserId")
	private java.lang.String updateUserId;//修改人ID
	@ApiModelProperty(value="修改时间",name="updateTime")
	private java.util.Date updateTime;//修改时间
	@ApiModelProperty(value="备注",name="remark")
	private java.lang.String remark;//备注
	@ApiModelProperty(value="删除标识0否1是",name="delFlag")
	private Integer delFlag;//删除标识0否1是
	@ApiModelProperty(value="状态0禁用1启用",name="status")
	private Integer status;//状态0禁用1启用
	@ApiModelProperty(value="用户ID",name="userId")
	private java.lang.String userId;//用户ID
	@ApiModelProperty(value="岗位ID",name="postId")
	private java.lang.String postId;//岗位ID
	/**
	 * id的getter方法
	 */
	public java.lang.String getUserPostId(){
		return userPostId;
	}
	/**
	 * id的setter方法
	 */
	public void setUserPostId(java.lang.String userPostId){
		this.userPostId = userPostId;
	}
	/**
	 * 创建人ID的getter方法
	 */
	public java.lang.String getCreateUserId(){
		return createUserId;
	}
	/**
	 * 创建人ID的setter方法
	 */
	public void setCreateUserId(java.lang.String createUserId){
		this.createUserId = createUserId;
	}
	/**
	 * 创建时间的getter方法
	 */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	 * 创建时间的setter方法
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 * 修改人ID的getter方法
	 */
	public java.lang.String getUpdateUserId(){
		return updateUserId;
	}
	/**
	 * 修改人ID的setter方法
	 */
	public void setUpdateUserId(java.lang.String updateUserId){
		this.updateUserId = updateUserId;
	}
	/**
	 * 修改时间的getter方法
	 */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	 * 修改时间的setter方法
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 * 备注的getter方法
	 */
	public java.lang.String getRemark(){
		return remark;
	}
	/**
	 * 备注的setter方法
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 * 删除标识0否1是的getter方法
	 */
	public Integer getDelFlag(){
		return delFlag;
	}
	/**
	 * 删除标识0否1是的setter方法
	 */
	public void setDelFlag(Integer delFlag){
		this.delFlag = delFlag;
	}
	/**
	 * 状态0禁用1启用的getter方法
	 */
	public Integer getStatus(){
		return status;
	}
	/**
	 * 状态0禁用1启用的setter方法
	 */
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	 * 用户ID的getter方法
	 */
	public java.lang.String getUserId(){
		return userId;
	}
	/**
	 * 用户ID的setter方法
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 * 岗位ID的getter方法
	 */
	public java.lang.String getPostId(){
		return postId;
	}
	/**
	 * 岗位ID的setter方法
	 */
	public void setPostId(java.lang.String postId){
		this.postId = postId;
	}
}
