package com.devplatform.admin.modules.sys.model;

/**
 * 用户岗位关系的Model
 * <br>
 * @author 代码生成器产生
 */
public class SysUserPostModel{
	
	private java.lang.String userPostId;//id
	private java.lang.String createUserId;//创建人ID
	private java.util.Date createTime;//创建时间
	private java.lang.String updateUserId;//修改人ID
	private java.util.Date updateTime;//修改时间
	private java.lang.String remark;//备注
	private Integer delFlag;//删除标识0否1是
	private Integer status;//状态0禁用1启用
	private java.lang.String userId;//用户ID
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
