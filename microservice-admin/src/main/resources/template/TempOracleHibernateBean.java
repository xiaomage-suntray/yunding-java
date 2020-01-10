package  com.sunztech.supervision.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author
 * ${bean.tableRemark}
 */
@TableName("${bean.tableName}")
@SuppressWarnings("serial")
public class ${bean.classOracleName} implements java.io.Serializable{
    
   


    <#list bean.fields as field>
    /**
     * ${field.fieldRemark}
     */
    private ${field.fieldJavaType} ${field.fieldJavaName};
    </#list>
    
    <#list bean.fields as field>
   
    @Column(name ="${field.fieldName}",<#if field.canBeNull=="true">nullable=true</#if><#if field.canBeNull=="false">nullable=false</#if><#if field.canBeNull=="null">nullable=false</#if><#if field.maxLength??>,length=${field.maxLength}</#if>)
    public ${field.fieldJavaType} ${field.getMethodName}(){
        return ${field.fieldJavaName};
    }
 
    public void ${field.setMethodName}(${field.fieldJavaType} ${field.fieldJavaName}){
        this.${field.fieldJavaName} = ${field.fieldJavaName};
    }
    </#list>

}
