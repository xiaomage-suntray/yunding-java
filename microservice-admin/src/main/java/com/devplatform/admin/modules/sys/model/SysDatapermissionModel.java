package com.devplatform.admin.modules.sys.model;

/**
 * 数据权限表的Model
 * <br>
 * @author 代码生成器产生
 */
public class SysDatapermissionModel{
	
	private java.lang.String datapermissionId;//id
	private java.lang.String createUserId;//创建人ID
	private java.util.Date createTime;//创建时间
	private java.lang.String updateUserId;//修改人ID
	private java.util.Date updateTime;//修改时间
	private java.lang.String remark;//备注
	private Integer delFlag;//删除标识0否1是
	private Integer status;//状态0禁用1启用
	private java.lang.String name;//权限名称
	private java.lang.String url;//权限指定地址
	private java.lang.String addparam;//动态参数
	private java.lang.String parentId;//父ID
	/**
	 * id的getter方法
	 */
	public java.lang.String getDatapermissionId(){
		return datapermissionId;
	}
	/**
	 * id的setter方法
	 */
	public void setDatapermissionId(java.lang.String datapermissionId){
		this.datapermissionId = datapermissionId;
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
	 * 权限名称的getter方法
	 */
	public java.lang.String getName(){
		return name;
	}
	/**
	 * 权限名称的setter方法
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 * 权限指定地址的getter方法
	 */
	public java.lang.String getUrl(){
		return url;
	}
	/**
	 * 权限指定地址的setter方法
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
	}
	/**
	 * 动态参数的getter方法
	 */
	public java.lang.String getAddparam(){
		return addparam;
	}
	/**
	 * 动态参数的setter方法
	 */
	public void setAddparam(java.lang.String addparam){
		this.addparam = addparam;
	}
	/**
	 * 父ID的getter方法
	 */
	public java.lang.String getParentId(){
		return parentId;
	}
	/**
	 * 父ID的setter方法
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
}
