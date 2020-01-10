package com.devplatform.admin.business.carNumberLimit.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="车辆限行对象",description="车辆限行对象")//swagger注解
@TableName("car_number_limit")//mybatisplus注解
public class CarNumberLimit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//

	@TableField(exist = false)
	private List<String> ids;

	@ApiModelProperty(value="城市名",name="cityname")
	private java.lang.String cityname;//城市名
	@ApiModelProperty(value="状态",name="status")
	private Integer status;//城市名

	@TableField(exist = false)
	private List<String> cityNameList;

	@ApiModelProperty(value="限行开始时间",name="startTime")
	private String startTime;//限行开始时间
	@ApiModelProperty(value="限行结束时间",name="endTime")
	private String endTime;//限行结束时间
	@ApiModelProperty(value="限行规则",name="rules")
	private java.lang.String rules;//限行规则
	@ApiModelProperty(value="创建时间",name="createDate")
	private java.util.Date createDate;//创建时间
	/**
	 * 的getter方法
	 */
	public java.lang.String getId(){
		return id;
	}
	/**
	 * 的setter方法
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 * 城市名的getter方法
	 */
	public java.lang.String getCityname(){
		return cityname;
	}
	/**
	 * 城市名的setter方法
	 */
	public void setCityname(java.lang.String cityname){
		this.cityname = cityname;
	}

	/**
	 * 限行规则的getter方法
	 */
	public java.lang.String getRules(){
		return rules;
	}
	/**
	 * 限行规则的setter方法
	 */
	public void setRules(java.lang.String rules){
		this.rules = rules;
	}
	/**
	 * 创建时间的getter方法
	 */
	public java.util.Date getCreateDate(){
		return createDate;
	}
	/**
	 * 创建时间的setter方法
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}

	public List<String> getCityNameList() {
		return cityNameList;
	}

	public void setCityNameList(List<String> cityNameList) {
		this.cityNameList = cityNameList;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
}
