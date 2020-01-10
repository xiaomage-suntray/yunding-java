package com.devplatform.admin.business.weatherInfo.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="天气预报对象",description="天气预报对象")//swagger注解
@TableName("weatherInfo")//mybatisplus注解
public class Weatherinfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//
	@ApiModelProperty(value="城市名",name="city")
	private java.lang.String city;//城市名
	@ApiModelProperty(value="状态",name="status")
	private java.lang.String status;//状态
	@ApiModelProperty(value="次数",name="count")
	private java.lang.String count;//次数
	@ApiModelProperty(value="infocode",name="infocode")
	private java.lang.String infocode;//infocode
	@ApiModelProperty(value="创建时间",name="adcode")
	private java.lang.String adcode;//创建时间
	@ApiModelProperty(value="天气",name="weather")
	private java.lang.String weather;//天气
	@ApiModelProperty(value="温度",name="temperature")
	private java.lang.String temperature;//温度
	@ApiModelProperty(value="风向",name="winddirection")
	private java.lang.String winddirection;//风向
	@ApiModelProperty(value="风力",name="windpowe")
	private java.lang.String windpowe;//风力
	@ApiModelProperty(value="湿度",name="humidity")
	private java.lang.String humidity;//湿度
	@ApiModelProperty(value="数据更新时间",name="reporttime")
	private java.lang.String reporttime;//数据更新时间

	@TableField(exist = false)
	private List<Casts> casts;
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
	public java.lang.String getCity(){
		return city;
	}
	/**
	 * 城市名的setter方法
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 * 状态的getter方法
	 */
	public java.lang.String getStatus(){
		return status;
	}
	/**
	 * 状态的setter方法
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 * 次数的getter方法
	 */
	public java.lang.String getCount(){
		return count;
	}
	/**
	 * 次数的setter方法
	 */
	public void setCount(java.lang.String count){
		this.count = count;
	}
	/**
	 * infocode的getter方法
	 */
	public java.lang.String getInfocode(){
		return infocode;
	}
	/**
	 * infocode的setter方法
	 */
	public void setInfocode(java.lang.String infocode){
		this.infocode = infocode;
	}
	/**
	 * 创建时间的getter方法
	 */
	public java.lang.String getAdcode(){
		return adcode;
	}
	/**
	 * 创建时间的setter方法
	 */
	public void setAdcode(java.lang.String adcode){
		this.adcode = adcode;
	}
	/**
	 * 天气的getter方法
	 */
	public java.lang.String getWeather(){
		return weather;
	}
	/**
	 * 天气的setter方法
	 */
	public void setWeather(java.lang.String weather){
		this.weather = weather;
	}
	/**
	 * 温度的getter方法
	 */
	public java.lang.String getTemperature(){
		return temperature;
	}
	/**
	 * 温度的setter方法
	 */
	public void setTemperature(java.lang.String temperature){
		this.temperature = temperature;
	}
	/**
	 * 风向的getter方法
	 */
	public java.lang.String getWinddirection(){
		return winddirection;
	}
	/**
	 * 风向的setter方法
	 */
	public void setWinddirection(java.lang.String winddirection){
		this.winddirection = winddirection;
	}
	/**
	 * 风力的getter方法
	 */
	public java.lang.String getWindpowe(){
		return windpowe;
	}
	/**
	 * 风力的setter方法
	 */
	public void setWindpowe(java.lang.String windpowe){
		this.windpowe = windpowe;
	}
	/**
	 * 湿度的getter方法
	 */
	public java.lang.String getHumidity(){
		return humidity;
	}
	/**
	 * 湿度的setter方法
	 */
	public void setHumidity(java.lang.String humidity){
		this.humidity = humidity;
	}
	/**
	 * 数据更新时间的getter方法
	 */
	public java.lang.String getReporttime(){
		return reporttime;
	}
	/**
	 * 数据更新时间的setter方法
	 */
	public void setReporttime(java.lang.String reporttime){
		this.reporttime = reporttime;
	}

	public List<Casts> getCasts() {
		return casts;
	}

	public void setCasts(List<Casts> casts) {
		this.casts = casts;
	}
}
