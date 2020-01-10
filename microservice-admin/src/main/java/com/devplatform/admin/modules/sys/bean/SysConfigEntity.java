package com.devplatform.admin.modules.sys.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:43:36
 */
@ApiModel(value="系统配置信息对象",description="系统配置信息")
@TableName("sys_config")
public class SysConfigEntity {
	@ApiModelProperty(value="主键ID",name="id")
	@TableId
	private String id;
	
	@ApiModelProperty(value="参数名",name="paramKey")
	@NotBlank(message="参数名不能为空")
	private String paramKey;
	
	@ApiModelProperty(value="参数值",name="paramValue")
	@NotBlank(message="参数值不能为空")
	private String paramValue;
	
	@ApiModelProperty(value="备注",name="remark")
	private String remark;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
