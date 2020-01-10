package com.devplatform.admin.business.wechat.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="微信保存accessToken对象",description="微信保存accessToken对象")//swagger注解
@TableName("weixin_access_token")//mybatisplus注解
public class WeixinAccessToken implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//主键
	@ApiModelProperty(value="token",name="accessToken")
	private java.lang.String accessToken;//token
	@ApiModelProperty(value="time",name="createdTime")
	private java.util.Date createdTime;//time
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
	 * token的getter方法
	 */
	public java.lang.String getAccessToken(){
		return accessToken;
	}
	/**
	 * token的setter方法
	 */
	public void setAccessToken(java.lang.String accessToken){
		this.accessToken = accessToken;
	}
	/**
	 * time的getter方法
	 */
	public java.util.Date getCreatedTime(){
		return createdTime;
	}
	/**
	 * time的setter方法
	 */
	public void setCreatedTime(java.util.Date createdTime){
		this.createdTime = createdTime;
	}
}
