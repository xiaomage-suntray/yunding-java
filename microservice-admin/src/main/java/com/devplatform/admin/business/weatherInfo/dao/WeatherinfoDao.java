package com.devplatform.admin.business.weatherInfo.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.business.weatherInfo.bean.Weatherinfo;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * Weatherinfo Mapper
 * 用于天气预报的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface WeatherinfoDao extends MyBaseMapper<Weatherinfo> {
	
	
}
