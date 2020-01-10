package com.devplatform.admin.business.weatherInfo.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.business.weatherInfo.bean.WeatherinfoForecasts;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * WeatherinfoForecasts Mapper
 * 用于预报数据的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface WeatherinfoForecastsDao extends MyBaseMapper<WeatherinfoForecasts> {
	
	
}
