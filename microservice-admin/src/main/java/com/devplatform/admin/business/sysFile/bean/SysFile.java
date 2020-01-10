package com.devplatform.admin.business.sysFile.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="系统文件表对象",description="系统文件表对象")//swagger注解
@TableName("sys_file")//mybatisplus注解
public class SysFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private java.lang.String id;//主键id
	@ApiModelProperty(value="创建人",name="createdUserId")
	private java.lang.String createdUserId;//创建人
	@ApiModelProperty(value="创建时间",name="createdTime")
	private java.util.Date createdTime;//创建时间
	@ApiModelProperty(value="更新人",name="updatedUserId")
	private java.lang.String updatedUserId;//更新人
	@ApiModelProperty(value="更新时间",name="updatedTime")
	private java.util.Date updatedTime;//更新时间
	@ApiModelProperty(value="文件url",name="fileUrl")
	private java.lang.String fileUrl;//文件url
	@ApiModelProperty(value="文件名",name="fileName")
	private java.lang.String fileName;//文件名
	@ApiModelProperty(value="文件类型",name="fileType")
	private java.lang.String fileType;//文件类型
	@TableField(exist = false)
	private String filePath;  //文件夹

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 主键id的getter方法
	 */
	public java.lang.String getId(){
		return id;
	}
	/**
	 * 主键id的setter方法
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 * 创建人的getter方法
	 */
	public java.lang.String getCreatedUserId(){
		return createdUserId;
	}
	/**
	 * 创建人的setter方法
	 */
	public void setCreatedUserId(java.lang.String createdUserId){
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
	public java.lang.String getUpdatedUserId(){
		return updatedUserId;
	}
	/**
	 * 更新人的setter方法
	 */
	public void setUpdatedUserId(java.lang.String updatedUserId){
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
	 * 文件url的getter方法
	 */
	public java.lang.String getFileUrl(){
		return fileUrl;
	}
	/**
	 * 文件url的setter方法
	 */
	public void setFileUrl(java.lang.String fileUrl){
		this.fileUrl = fileUrl;
	}
	/**
	 * 文件名的getter方法
	 */
	public java.lang.String getFileName(){
		return fileName;
	}
	/**
	 * 文件名的setter方法
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
	}
	/**
	 * 文件类型的getter方法
	 */
	public java.lang.String getFileType(){
		return fileType;
	}
	/**
	 * 文件类型的setter方法
	 */
	public void setFileType(java.lang.String fileType){
		this.fileType = fileType;
	}
}
