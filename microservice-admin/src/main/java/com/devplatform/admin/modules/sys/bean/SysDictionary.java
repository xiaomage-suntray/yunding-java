package com.devplatform.admin.modules.sys.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="字典表对象",description="字典表对象")//swagger注解
@TableName("sys_dictionary")//mybatisplus注解
public class SysDictionary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private String id;//主键id
	@ApiModelProperty(value="创建人",name="createdUserId")
	private String createdUserId;//创建人
	@ApiModelProperty(value="创建时间",name="createdTime")
	private java.util.Date createdTime;//创建时间
	@ApiModelProperty(value="更新人",name="updatedUserId")
	private String updatedUserId;//更新人
	@ApiModelProperty(value="更新时间",name="updatedTime")
	private java.util.Date updatedTime;//更新时间
	@ApiModelProperty(value="字典类型",name="dictionaryCalss")
	private String dictionaryCalss;//字典类型
	@ApiModelProperty(value="字典名称",name="dictionaryName")
	private String dictionaryName;//字典名称
	@ApiModelProperty(value="字典编码",name="dictionaryCode")
	private String dictionaryCode;//字典编码
	/**
	 * 主键id的getter方法
	 */
	public String getId(){
		return id;
	}
	/**
	 * 主键id的setter方法
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 创建人的getter方法
	 */
	public String getCreatedUserId(){
		return createdUserId;
	}
	/**
	 * 创建人的setter方法
	 */
	public void setCreatedUserId(String createdUserId){
		this.createdUserId = createdUserId;
	}
	/**
	 * 创建时间的getter方法
	 */
	public java.util.Date getCreatedTime(){
		return createdTime;
	}
	/**
	 * 创建时间的setter方法
	 */
	public void setCreatedTime(java.util.Date createdTime){
		this.createdTime = createdTime;
	}
	/**
	 * 更新人的getter方法
	 */
	public String getUpdatedUserId(){
		return updatedUserId;
	}
	/**
	 * 更新人的setter方法
	 */
	public void setUpdatedUserId(String updatedUserId){
		this.updatedUserId = updatedUserId;
	}
	/**
	 * 更新时间的getter方法
	 */
	public java.util.Date getUpdatedTime(){
		return updatedTime;
	}
	/**
	 * 更新时间的setter方法
	 */
	public void setUpdatedTime(java.util.Date updatedTime){
		this.updatedTime = updatedTime;
	}
	/**
	 * 字典类型的getter方法
	 */
	public String getDictionaryCalss(){
		return dictionaryCalss;
	}
	/**
	 * 字典类型的setter方法
	 */
	public void setDictionaryCalss(String dictionaryCalss){
		this.dictionaryCalss = dictionaryCalss;
	}
	/**
	 * 字典名称的getter方法
	 */
	public String getDictionaryName(){
		return dictionaryName;
	}
	/**
	 * 字典名称的setter方法
	 */
	public void setDictionaryName(String dictionaryName){
		this.dictionaryName = dictionaryName;
	}
	/**
	 * 字典编码的getter方法
	 */
	public String getDictionaryCode(){
		return dictionaryCode;
	}
	/**
	 * 字典编码的setter方法
	 */
	public void setDictionaryCode(String dictionaryCode){
		this.dictionaryCode = dictionaryCode;
	}
}
