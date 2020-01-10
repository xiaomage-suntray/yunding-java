package ${bean.pacageName}${bean.modulesName}.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@ApiModel(value="${bean.tableRemark}对象",description="${bean.tableRemark}对象")//swagger注解
@TableName("${bean.tableName}")//mybatisplus注解
public class ${bean.className} implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	<#list bean.fields as field>
	<#if field.fieldJavaName == "id">
	@ApiModelProperty(value="主键ID",name="${field.fieldJavaName}")
	@TableId
	private ${field.fieldJavaType} ${field.fieldJavaName};//${field.fieldRemark}
	<#else >
	@ApiModelProperty(value="${field.fieldRemark}",name="${field.fieldJavaName}")
	private ${field.fieldJavaType} ${field.fieldJavaName};//${field.fieldRemark}
	</#if>
	</#list>
	<#list bean.fields as field>
	/**
	 * ${field.fieldRemark}的getter方法
	 */
	public ${field.fieldJavaType} ${field.getMethodName}(){
		return ${field.fieldJavaName};
	}
	/**
	 * ${field.fieldRemark}的setter方法
	 */
	public void ${field.setMethodName}(${field.fieldJavaType} ${field.fieldJavaName}){
		this.${field.fieldJavaName} = ${field.fieldJavaName};
	}
	</#list>
}
