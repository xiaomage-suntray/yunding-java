package com.devplatform.admin.modules.sys.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="demo演示权限对象",description="demo演示权限对象")//swagger注解
@TableName("demo")//mybatisplus注解
public class Demo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//主键
	@ApiModelProperty(value="订单号",name="orderSn")
	private java.lang.String orderSn;//订单号
	@ApiModelProperty(value="订单名称",name="orderName")
	private java.lang.String orderName;//订单名称
	@ApiModelProperty(value="订单金额",name="orderAmount")
	private Double orderAmount;//订单金额
	@ApiModelProperty(value="创建时间",name="createTime")
	private java.util.Date createTime;//创建时间
	@ApiModelProperty(value="创建人",name="createUserId")
	private java.lang.String createUserId;//创建人
	@ApiModelProperty(value="备注",name="remark")
	private java.lang.String remark;//备注
	@ApiModelProperty(value="组织机构ID",name="orgId")
	private java.lang.String orgId;//组织机构ID
	@ApiModelProperty(value="组织机构CODE",name="orgCode")
	private java.lang.String orgCode;//组织机构CODE
	@ApiModelProperty(value="组织机构名称",name="orgName")
	private java.lang.String orgName;//组织机构名称
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
	 * 订单号的getter方法
	 */
	public java.lang.String getOrderSn(){
		return orderSn;
	}
	/**
	 * 订单号的setter方法
	 */
	public void setOrderSn(java.lang.String orderSn){
		this.orderSn = orderSn;
	}
	/**
	 * 订单名称的getter方法
	 */
	public java.lang.String getOrderName(){
		return orderName;
	}
	/**
	 * 订单名称的setter方法
	 */
	public void setOrderName(java.lang.String orderName){
		this.orderName = orderName;
	}
	/**
	 * 订单金额的getter方法
	 */
	public Double getOrderAmount(){
		return orderAmount;
	}
	/**
	 * 订单金额的setter方法
	 */
	public void setOrderAmount(Double orderAmount){
		this.orderAmount = orderAmount;
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
	 * 创建人的getter方法
	 */
	public java.lang.String getCreateUserId(){
		return createUserId;
	}
	/**
	 * 创建人的setter方法
	 */
	public void setCreateUserId(java.lang.String createUserId){
		this.createUserId = createUserId;
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
	 * 组织机构CODE的getter方法
	 */
	public java.lang.String getOrgCode(){
		return orgCode;
	}
	/**
	 * 组织机构CODE的setter方法
	 */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
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
}
