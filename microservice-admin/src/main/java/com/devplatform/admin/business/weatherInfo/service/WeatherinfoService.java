package com.devplatform.admin.business.weatherInfo.service;

import com.devplatform.common.service.MyBaseService;

import com.devplatform.admin.business.weatherInfo.bean.Weatherinfo;

/**
 * 天气预报的service接口
 * <br>
 * <b>功能：</b>WeatherinfoService<br>
 * @author 代码生成器产生
 */
public interface WeatherinfoService extends MyBaseService<Weatherinfo> {


    /**
     * 获取天气预报
     */
    void getGaodeWeathrinfo() throws Exception;
}
