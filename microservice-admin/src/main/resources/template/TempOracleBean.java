package com.${bean.pacageName}.bean;
import java.io.Serializable;
import java.util.Date;
import com.cnpay.po.TableName;
/**
 * @author
 * ${bean.tableRemark}
 */
@TableName("${bean.tableName}")
public class ${bean.classOracleName} implements Serializable {
	
	<#list bean.fields as field>
	/**
	 * ${field.fieldRemark}
	 */
	private ${field.fieldJavaType} ${field.fieldJavaName};
	</#list>
	<#list bean.fields as field>
	public ${field.fieldJavaType} ${field.getMethodName}(){
		return ${field.fieldJavaName};
	}
	public void ${field.setMethodName}(${field.fieldJavaType} ${field.fieldJavaName}){
		this.${field.fieldJavaName} = ${field.fieldJavaName};
	}
	</#list>
}
