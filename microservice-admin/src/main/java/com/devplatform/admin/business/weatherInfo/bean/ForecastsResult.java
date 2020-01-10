package com.devplatform.admin.business.weatherInfo.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class ForecastsResult implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String status;//状态
	private String count;//次数
	private String infocode;//infocode
	private String info;//infocode
	private List<Forecasts> forecasts;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getInfocode() {
		return infocode;
	}

	public void setInfocode(String infocode) {
		this.infocode = infocode;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<Forecasts> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<Forecasts> forecasts) {
		this.forecasts = forecasts;
	}
}
