package com.devplatform.admin.business.sysFile.model;

/**
 * 系统文件表的Model
 * <br>
 * @author 代码生成器产生
 */
public class SysFileModel{
	
	private java.lang.String id;//主键id
	private java.lang.String createdUserId;//创建人
	private java.util.Date createdTime;//创建时间
	private java.lang.String updatedUserId;//更新人
	private java.util.Date updatedTime;//更新时间
	private java.lang.String fileUrl;//文件url
	private java.lang.String fileName;//文件名
	private java.lang.String fileType;//文件类型
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
