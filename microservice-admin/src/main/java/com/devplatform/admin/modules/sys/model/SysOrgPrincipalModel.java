package com.devplatform.admin.modules.sys.model;

/**
 * 组织机构与负责人关联关系表的Model
 * <br>
 * @author 代码生成器产生
 */
public class SysOrgPrincipalModel{
	
	private java.lang.String orgPrincipalId;//主键
	private java.lang.String orgId;//组织机构ID
	private java.lang.String userId;//负责人ID
	private java.lang.String createUserId;//创建人ID
	private java.util.Date createTime;//创建时间
	/**
	 * 主键的getter方法
	 */
	public java.lang.String getOrgPrincipalId(){
		return orgPrincipalId;
	}
	/**
	 * 主键的setter方法
	 */
	public void setOrgPrincipalId(java.lang.String orgPrincipalId){
		this.orgPrincipalId = orgPrincipalId;
	}
	/**
	 * 组织机构ID的getter方法
	 */
	public java.lang.String getOrgId(){
		return orgId;
	}
	/**
	 * 组织机构ID的setter方法
	 */
	public void setOrgId(java.lang.String orgId){
		this.orgId = orgId;
	}
	/**
	 * 负责人ID的getter方法
	 */
	public java.lang.String getUserId(){
		return userId;
	}
	/**
	 * 负责人ID的setter方法
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
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
}
