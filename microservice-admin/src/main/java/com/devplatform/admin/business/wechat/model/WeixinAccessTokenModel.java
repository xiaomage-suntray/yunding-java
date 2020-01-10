package com.devplatform.admin.business.wechat.model;

/**
 * 微信保存accessToken的Model
 * <br>
 * @author 代码生成器产生
 */
public class WeixinAccessTokenModel{
	
	private java.lang.String id;//主键
	private java.lang.String accessToken;//token
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
