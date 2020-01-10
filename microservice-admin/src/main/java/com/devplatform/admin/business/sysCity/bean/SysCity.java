package com.devplatform.admin.business.sysCity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="城市表对象",description="城市表对象")//swagger注解
@TableName("sys_city")//mybatisplus注解
public class SysCity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private String id;//
	@ApiModelProperty(value="城市名称",name="cityname")
	private String cityname;//城市名称
	@ApiModelProperty(value="城市ad编码",name="adcode")
	private String adcode;//城市ad编码
	@ApiModelProperty(value="城市编码",name="citycode")
	private String citycode;//城市编码
	/**
	 * 的getter方法
	 */
	public String getId(){
		return id;
	}
	/**
	 * 的setter方法
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 城市名称的getter方法
	 */
	public String getCityname(){
		return cityname;
	}
	/**
	 * 城市名称的setter方法
	 */
	public void setCityname(String cityname){
		this.cityname = cityname;
	}
	/**
	 * 城市ad编码的getter方法
	 */
	public String getAdcode(){
		return adcode;
	}
	/**
	 * 城市ad编码的setter方法
	 */
	public void setAdcode(String adcode){
		this.adcode = adcode;
	}
	/**
	 * 城市编码的getter方法
	 */
	public String getCitycode(){
		return citycode;
	}
	/**
	 * 城市编码的setter方法
	 */
	public void setCitycode(String citycode){
		this.citycode = citycode;
	}
}
