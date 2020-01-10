package com.devplatform.admin.modules.sys.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="系统表单明细对象",description="系统表单明细对象")//swagger注解
@TableName("sys_form_detail")//mybatisplus注解
public class SysFormDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//id
	@ApiModelProperty(value="表单ID",name="formId")
	private java.lang.String formId;//表单ID
	@ApiModelProperty(value="属性中文名称",name="discriptionName")
	private java.lang.String discriptionName;//属性中文名称
	@ApiModelProperty(value="属性英文名称",name="propertyName")
	private java.lang.String propertyName;//属性英文名称
	@ApiModelProperty(value="字段英文名称",name="columnName")
	private java.lang.String columnName;//字段英文名称
	@ApiModelProperty(value="创建时间",name="createTime")
	private java.util.Date createTime;//创建时间
	@ApiModelProperty(value="创建人ID",name="createUserId")
	private java.lang.String createUserId;//创建人ID
	@ApiModelProperty(value="修改人ID",name="updateUserId")
	private java.lang.String updateUserId;//修改人ID
	@ApiModelProperty(value="修改时间",name="updateTime")
	private java.util.Date updateTime;//修改时间
	@ApiModelProperty(value="备注",name="remark")
	private java.lang.String remark;//备注
	@ApiModelProperty(value="删除标识0否1是",name="delFlag")
	private Integer delFlag;//删除标识0否1是
	@ApiModelProperty(value="禁用disabled只读readonly编辑edit不显示display",name="status")
	private java.lang.String status;//禁用disabled只读readonly编辑edit不显示display
	@ApiModelProperty(value="类型(预留字段)",name="type")
	private Integer type;//类型(预留字段)
	@ApiModelProperty(value="排序",name="sort")
	private Integer sort;//排序
	/**
	 * id的getter方法
	 */
	public java.lang.String getId(){
		return id;
	}
	/**
	 * id的setter方法
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 * 表单ID的getter方法
	 */
	public java.lang.String getFormId(){
		return formId;
	}
	/**
	 * 表单ID的setter方法
	 */
	public void setFormId(java.lang.String formId){
		this.formId = formId;
	}
	/**
	 * 属性中文名称的getter方法
	 */
	public java.lang.String getDiscriptionName(){
		return discriptionName;
	}
	/**
	 * 属性中文名称的setter方法
	 */
	public void setDiscriptionName(java.lang.String discriptionName){
		this.discriptionName = discriptionName;
	}
	/**
	 * 属性英文名称的getter方法
	 */
	public java.lang.String getPropertyName(){
		return propertyName;
	}
	/**
	 * 属性英文名称的setter方法
	 */
	public void setPropertyName(java.lang.String propertyName){
		this.propertyName = propertyName;
	}
	/**
	 * 字段英文名称的getter方法
	 */
	public java.lang.String getColumnName(){
		return columnName;
	}
	/**
	 * 字段英文名称的setter方法
	 */
	public void setColumnName(java.lang.String columnName){
		this.columnName = columnName;
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
	 * 禁用disabled只读readonly编辑edit不显示display的getter方法
	 */
	public java.lang.String getStatus(){
		return status;
	}
	/**
	 * 禁用disabled只读readonly编辑edit不显示display的setter方法
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 * 类型(预留字段)的getter方法
	 */
	public Integer getType(){
		return type;
	}
	/**
	 * 类型(预留字段)的setter方法
	 */
	public void setType(Integer type){
		this.type = type;
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
}
