package com.devplatform.admin.modules.sys.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="code编号表对象",description="code编号表对象")//swagger注解
@TableName("sys_serial_number")//mybatisplus注解
public class SysSerialNumber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="自增序号",name="serialNumber")
	private Integer serialNumber;//自增序号
	/**
	 * 自增序号的getter方法
	 */
	public Integer getSerialNumber(){
		return serialNumber;
	}
	/**
	 * 自增序号的setter方法
	 */
	public void setSerialNumber(Integer serialNumber){
		this.serialNumber = serialNumber;
	}
}
