package com.devplatform.admin.modules.generation.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author liyinchu
 *
 */
@ApiModel(value="表字段属性对象",description="表字段属性")
public class FieldModel {
	/**
	 * 字段名称
	 */
	@ApiModelProperty(value="字段名称",name="fieldName")
	private String fieldName;
	/**
	 * java字段名称
	 */
	@ApiModelProperty(value="java字段名称",name="fieldJavaName")
	private String fieldJavaName;
	/**
	 * 字段备注
	 */
	@ApiModelProperty(value="字段备注",name="fieldRemark")
	private String fieldRemark;
	/**
	 * 字段类型
	 */
	@ApiModelProperty(value="字段类型",name="fieldType")
	private String fieldType;
	/**
	 * 字段类型
	 */
	@ApiModelProperty(value="字段类型",name="fieldJavaType")
	private String fieldJavaType;
	/**
	 * 可否为null
	 */
	@ApiModelProperty(value="可否为null",name="canBeNull")
	private String canBeNull;
	/**
	 * 最大长度
	 */
	@ApiModelProperty(value="最大长度",name="maxLength")
	private Integer maxLength;
	/**
	 * 最新长度
	 */
	@ApiModelProperty(value="最小长度",name="minLength")
	private Integer minLength;
	/**
	 * 是否邮箱
	 */
	@ApiModelProperty(value="是否邮箱",name="isEmail")
	private String isEmail;
	/**
	 * 是否手机号
	 */
	@ApiModelProperty(value="是否手机号",name="isMobile")
	private String isMobile;
	
	@ApiModelProperty(value="是否ID",name="isIdNo")
	private String isIdNo;
	/**
	 * 正则表达式
	 */
	@ApiModelProperty(value="正则表达式",name="regexString")
	private String regexString;
	/**
	 * 枚举值
	 */
	@ApiModelProperty(value="枚举值",name="enumString")
	private String enumString;
	/**
	 * java类的get方法的名称
	 */
	private String getMethodName;
	
	/**
	 * java类的set方法的名称
	 */
	private String setMethodName;
	
	public String getFieldName() {
		return fieldName.toLowerCase();
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldRemark() {
		
		if(fieldRemark == null ){
			return "";
		}else{
			fieldRemark = fieldRemark.replaceAll("\n", "").replaceAll("\r", "");
		    return fieldRemark;
		}
	}
	public void setFieldRemark(String fieldRemark) {
		this.fieldRemark = fieldRemark;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getCanBeNull() {
		return canBeNull;
	}
	public void setCanBeNull(String canBeNull) {
		this.canBeNull = canBeNull;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public Integer getMinLength() {
		return minLength;
	}
	public String toString() {
		return "FieldModel [fieldName=" + fieldName + ", fieldJavaName=" + fieldJavaName + ", fieldRemark=" + fieldRemark + ", fieldType=" + fieldType + ", canBeNull=" + canBeNull + ", maxLength=" + maxLength + ", minLength=" + minLength + ", isEmail=" + isEmail + ", isMobile=" + isMobile
				+ ", isIdNo=" + isIdNo + ", regexString=" + regexString + ", enumString=" + enumString + "]";
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public String getIsEmail() {
		return isEmail;
	}
	public void setIsEmail(String isEmail) {
		this.isEmail = isEmail;
	}
	public String getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	public String getRegexString() {
		return regexString;
	}
	public void setRegexString(String regexString) {
		this.regexString = regexString;
	}
	public String getEnumString() {
		return enumString;
	}
	public void setEnumString(String enumString) {
		this.enumString = enumString;
	}
	public String getFieldJavaName() {
		if(fieldName!=null){
			StringBuffer sb = new StringBuffer(fieldName.length());
			String tableNew = fieldName.toLowerCase();
			String[] tables = tableNew.split("_");
			String temp = null;
			for (int i = 0; i < tables.length; i++) {
				temp = tables[i].trim();
				sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
			}
			fieldJavaName = sb.toString();
			fieldJavaName = fieldJavaName.substring(0, 1).toLowerCase()+fieldJavaName.substring(1);
		}
		return fieldJavaName;
	}
	public void setFieldJavaName(String fieldJavaName) {
		this.fieldJavaName = fieldJavaName;
	}
	public String getIsIdNo() {
		return isIdNo;
	}
	public void setIsIdNo(String isIdNo) {
		this.isIdNo = isIdNo;
	}
	public String getFieldJavaType() {
		if(GeneratorConstant.getMap().get(fieldType.toUpperCase())==null){
			fieldJavaType = "String";
		}else{
			fieldJavaType = GeneratorConstant.getMap().get(fieldType.toUpperCase());
		}
		return fieldJavaType;
	}
	public void setFieldJavaType(String fieldJavaType) {
		this.fieldJavaType = fieldJavaType;
	}

	
	public String getGetMethodName() {
		if(fieldName!=null){
			StringBuffer sb = new StringBuffer(fieldName.length());
			String newstring = fieldName.toLowerCase();
			String[] newArray = newstring.split("_");
			String temp = null;
			for (int i = 0; i < newArray.length; i++) {
				temp = newArray[i].trim();
				sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
			}
			getMethodName = "get"+sb.toString();
			return getMethodName;
		}
		throw new CustomException("数据库字段名称不能为空", "0101");
	}
	public String getSetMethodName() {
		if(fieldName!=null){
			StringBuffer sb = new StringBuffer(fieldName.length());
			String newstring = fieldName.toLowerCase();
			String[] newArray = newstring.split("_");
			String temp = null;
			for (int i = 0; i < newArray.length; i++) {
				temp = newArray[i].trim();
				sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
			}
			setMethodName = "set"+sb.toString();
			return setMethodName;
		}
		throw new CustomException("数据库字段名称不能为空", "0101");
	}
}
