package com.devplatform.admin.modules.sys.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="系统表单对象",description="系统表单对象")//swagger注解
@TableName("sys_form")//mybatisplus注解
public class SysForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//主键
	@ApiModelProperty(value="表单名称",name="formName")
	private java.lang.String formName;//表单名称
	@ApiModelProperty(value="表单组合访问URL",name="url")
	private java.lang.String url;//表单组合访问URL
	@ApiModelProperty(value="表单唯一标志",name="identity")
	private java.lang.String identity;//表单唯一标志
	@ApiModelProperty(value="创建时间",name="createTime")
	private java.util.Date createTime;//创建时间
	@ApiModelProperty(value="删除标识0否1是",name="delFlag")
	private Integer delFlag;//删除标识0否1是
	@ApiModelProperty(value="备注",name="remark")
	private java.lang.String remark;//备注
	@ApiModelProperty(value="创建人ID",name="createUserId")
	private java.lang.String createUserId;//创建人ID
	@ApiModelProperty(value="修改人",name="updateUserId")
	private java.lang.String updateUserId;//修改人
	@ApiModelProperty(value="修改时间",name="updateTime")
	private java.util.Date updateTime;//修改时间
	@ApiModelProperty(value="状态0禁用1启用",name="status")
	private Integer status;//状态0禁用1启用
	@ApiModelProperty(value="上级ID",name="parentId")
	private java.lang.String parentId;//上级ID
	@ApiModelProperty(value="排序",name="sort")
	private Integer sort;//排序
	/**
	 * 主键的getter方法
	 */
	public java.lang.String getId(){
		return id;
	}
	/**
	 * 主键的setter方法
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 * 表单名称的getter方法
	 */
	public java.lang.String getFormName(){
		return formName;
	}
	/**
	 * 表单名称的setter方法
	 */
	public void setFormName(java.lang.String formName){
		this.formName = formName;
	}
	/**
	 * 表单组合访问URL的getter方法
	 */
	public java.lang.String getUrl(){
		return url;
	}
	/**
	 * 表单组合访问URL的setter方法
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
	}
	/**
	 * 表单唯一标志的getter方法
	 */
	public java.lang.String getIdentity(){
		return identity;
	}
	/**
	 * 表单唯一标志的setter方法
	 */
	public void setIdentity(java.lang.String identity){
		this.identity = identity;
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
	 * 修改人的getter方法
	 */
	public java.lang.String getUpdateUserId(){
		return updateUserId;
	}
	/**
	 * 修改人的setter方法
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
