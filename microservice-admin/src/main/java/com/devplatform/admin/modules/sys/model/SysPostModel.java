package com.devplatform.admin.modules.sys.model;

/**
 * 岗位管理的Model
 * <br>
 * @author 代码生成器产生
 */
public class SysPostModel{
	
	private java.lang.String postId;//id
	private java.lang.String createUserId;//创建人ID
	private java.util.Date createTime;//创建时间
	private java.lang.String updateUserId;//修改人ID
	private java.util.Date updateTime;//修改时间
	private java.lang.String remark;//备注
	private Integer delFlag;//删除标识0否1是
	private Integer status;//状态0禁用1启用
	private java.lang.String parentId;//父级ID
	private java.lang.String postName;//岗位名称
	private Integer sort;//排序
	private Integer level;//等级
	private java.lang.String postCode;//岗位code
	/**
	 * id的getter方法
	 */
	public java.lang.String getPostId(){
		return postId;
	}
	/**
	 * id的setter方法
	 */
	public void setPostId(java.lang.String postId){
		this.postId = postId;
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
	 * 父级ID的getter方法
	 */
	public java.lang.String getParentId(){
		return parentId;
	}
	/**
	 * 父级ID的setter方法
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
	/**
	 * 岗位名称的getter方法
	 */
	public java.lang.String getPostName(){
		return postName;
	}
	/**
	 * 岗位名称的setter方法
	 */
	public void setPostName(java.lang.String postName){
		this.postName = postName;
	}
	/**
	 * 排序的getter方法
	 */
	public Integer getSort(){
		return sort;
	}
	/**
	 * 排序的setter方法
	 */
	public void setSort(Integer sort){
		this.sort = sort;
	}
	/**
	 * 等级的getter方法
	 */
	public Integer getLevel(){
		return level;
	}
	/**
	 * 等级的setter方法
	 */
	public void setLevel(Integer level){
		this.level = level;
	}
	/**
	 * 岗位code的getter方法
	 */
	public java.lang.String getPostCode(){
		return postCode;
	}
	/**
	 * 岗位code的setter方法
	 */
	public void setPostCode(java.lang.String postCode){
		this.postCode = postCode;
	}
}
