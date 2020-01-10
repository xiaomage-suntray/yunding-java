package com.devplatform.admin.modules.sys.model;

/**
 * 组织机构管理的Model
 * <br>
 * @author 代码生成器产生
 */
public class SysOrgModel{
	
	private java.lang.String orgId;//id
	private java.lang.String createUserId;//创建人ID
	private java.util.Date createTime;//创建时间
	private java.lang.String updateUserId;//修改人ID
	private java.util.Date updateTime;//修改时间
	private java.lang.String remark;//备注
	private Integer delFlag;//删除标识0否1是
	private Integer status;//状态0禁用1启用
	private java.lang.String parentId;//上级ID
	private java.lang.String orgName;//组织机构名称
	private Integer type;//类型
	private Integer level;//等级
	private Integer sort;//排序
	private java.lang.String orgCode;//组织机构编码
	private java.lang.String isCorporation;//是否公司
	/**
	 * id的getter方法
	 */
	public java.lang.String getOrgId(){
		return orgId;
	}
	/**
	 * id的setter方法
	 */
	public void setOrgId(java.lang.String orgId){
		this.orgId = orgId;
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
	 * 上级ID的getter方法
	 */
	public java.lang.String getParentId(){
		return parentId;
	}
	/**
	 * 上级ID的setter方法
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
	/**
	 * 组织机构名称的getter方法
	 */
	public java.lang.String getOrgName(){
		return orgName;
	}
	/**
	 * 组织机构名称的setter方法
	 */
	public void setOrgName(java.lang.String orgName){
		this.orgName = orgName;
	}
	/**
	 * 类型的getter方法
	 */
	public Integer getType(){
		return type;
	}
	/**
	 * 类型的setter方法
	 */
	public void setType(Integer type){
		this.type = type;
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
	 * 组织机构编码的getter方法
	 */
	public java.lang.String getOrgCode(){
		return orgCode;
	}
	/**
	 * 组织机构编码的setter方法
	 */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
	}
	public java.lang.String getIsCorporation() {
		return isCorporation;
	}
	public void setIsCorporation(java.lang.String isCorporation) {
		this.isCorporation = isCorporation;
	}
	
}
