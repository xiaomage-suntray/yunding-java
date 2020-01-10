package ${bean.pacageName}${bean.modulesName}.model;

/**
 * ${bean.tableRemark}的Model
 * <br>
 * @author 代码生成器产生
 */
public class ${bean.className}Model{
	
	<#list bean.fields as field>
	private ${field.fieldJavaType} ${field.fieldJavaName};//${field.fieldRemark}
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
