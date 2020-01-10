package com.devplatform.admin.business.weatherInfo.bean;

import java.io.Serializable;
import java.util.List;

public class Forecasts implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String city;
	private String adcode;
	private String province;
	private String reporttime;
	private String infocode;
	private List<Casts> casts;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getInfocode() {
		return infocode;
	}

	public void setInfocode(String infocode) {
		this.infocode = infocode;
	}

	public List<Casts> getCasts() {
		return casts;
	}

	public void setCasts(List<Casts> casts) {
		this.casts = casts;
	}
}
