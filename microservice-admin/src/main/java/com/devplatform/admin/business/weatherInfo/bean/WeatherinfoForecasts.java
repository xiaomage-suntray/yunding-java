package com.devplatform.admin.business.weatherInfo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="预报数据对象",description="预报数据对象")//swagger注解
@TableName("weatherInfo_forecasts")//mybatisplus注解
public class WeatherinfoForecasts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//
	@ApiModelProperty(value="时间",name="date")
	private java.lang.String date;//时间
	@ApiModelProperty(value="星期几",name="week")
	private java.lang.String week;//星期几
	@ApiModelProperty(value="白天天气现象",name="dayweather")
	private java.lang.String dayweather;//白天天气现象
	@ApiModelProperty(value="晚上天气现象",name="nightweather")
	private java.lang.String nightweather;//晚上天气现象
	@ApiModelProperty(value="白天温度",name="daytemp")
	private java.lang.String daytemp;//白天温度
	@ApiModelProperty(value="晚上温度",name="nighttemp")
	private java.lang.String nighttemp;//晚上温度
	@ApiModelProperty(value="白天风向",name="daywind")
	private java.lang.String daywind;//白天风向
	@ApiModelProperty(value="晚上风向",name="nightwind")
	private java.lang.String nightwind;//晚上风向
	@ApiModelProperty(value="白天风力",name="daypower")
	private java.lang.String daypower;//白天风力
	@ApiModelProperty(value="晚上风力",name="nightpower")
	private java.lang.String nightpower;//晚上风力
	@ApiModelProperty(value="晚上风力",name="adcode")
	private java.lang.String adcode;//晚上风力
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
	 * 时间的getter方法
	 */
	public java.lang.String getDate(){
		return date;
	}
	/**
	 * 时间的setter方法
	 */
	public void setDate(java.lang.String date){
		this.date = date;
	}
	/**
	 * 星期几的getter方法
	 */
	public java.lang.String getWeek(){
		return week;
	}
	/**
	 * 星期几的setter方法
	 */
	public void setWeek(java.lang.String week){
		this.week = week;
	}
	/**
	 * 白天天气现象的getter方法
	 */
	public java.lang.String getDayweather(){
		return dayweather;
	}
	/**
	 * 白天天气现象的setter方法
	 */
	public void setDayweather(java.lang.String dayweather){
		this.dayweather = dayweather;
	}
	/**
	 * 晚上天气现象的getter方法
	 */
	public java.lang.String getNightweather(){
		return nightweather;
	}
	/**
	 * 晚上天气现象的setter方法
	 */
	public void setNightweather(java.lang.String nightweather){
		this.nightweather = nightweather;
	}
	/**
	 * 白天温度的getter方法
	 */
	public java.lang.String getDaytemp(){
		return daytemp;
	}
	/**
	 * 白天温度的setter方法
	 */
	public void setDaytemp(java.lang.String daytemp){
		this.daytemp = daytemp;
	}
	/**
	 * 晚上温度的getter方法
	 */
	public java.lang.String getNighttemp(){
		return nighttemp;
	}
	/**
	 * 晚上温度的setter方法
	 */
	public void setNighttemp(java.lang.String nighttemp){
		this.nighttemp = nighttemp;
	}
	/**
	 * 白天风向的getter方法
	 */
	public java.lang.String getDaywind(){
		return daywind;
	}
	/**
	 * 白天风向的setter方法
	 */
	public void setDaywind(java.lang.String daywind){
		this.daywind = daywind;
	}
	/**
	 * 晚上风向的getter方法
	 */
	public java.lang.String getNightwind(){
		return nightwind;
	}
	/**
	 * 晚上风向的setter方法
	 */
	public void setNightwind(java.lang.String nightwind){
		this.nightwind = nightwind;
	}
	/**
	 * 白天风力的getter方法
	 */
	public java.lang.String getDaypower(){
		return daypower;
	}
	/**
	 * 白天风力的setter方法
	 */
	public void setDaypower(java.lang.String daypower){
		this.daypower = daypower;
	}
	/**
	 * 晚上风力的getter方法
	 */
	public java.lang.String getNightpower(){
		return nightpower;
	}
	/**
	 * 晚上风力的setter方法
	 */
	public void setNightpower(java.lang.String nightpower){
		this.nightpower = nightpower;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}
}
