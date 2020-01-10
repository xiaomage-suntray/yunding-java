package com.devplatform.admin.business.weatherInfo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.business.weatherInfo.bean.*;
import com.devplatform.admin.business.weatherInfo.service.WeatherinfoForecastsService;
import com.devplatform.admin.common.utils.JSONUtil;
import com.devplatform.common.util.HttpClientUtil;
import io.swagger.annotations.ApiModelProperty;
import org.apache.http.NameValuePair;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.devplatform.admin.business.weatherInfo.dao.WeatherinfoDao;
import com.devplatform.admin.business.weatherInfo.service.WeatherinfoService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

import java.util.*;

/**
 * 天气预报的service接口实现类
 * <br>
 * <b>功能：</b>WeatherinfoServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("weatherinfoService")
public class WeatherinfoServiceImpl extends MyBaseServiceImpl<WeatherinfoDao, Weatherinfo> implements WeatherinfoService {

    @Autowired
    private WeatherinfoForecastsService weatherinfoForecastsService;


    @Value("${gaode.key}")
    private String key;
    /**
     * 获取天气情况
     */
    @Async
    @Override
    public void getGaodeWeathrinfo() throws Exception {

        List<Weatherinfo> list  = baseMapper.selectList(null);
        if(list!=null && list.size()>0){
            for(Weatherinfo bean:list){
                //查实时
                Object [] params = new Object[]{"key","city","output"};
                Object [] values = new Object[]{key,bean.getAdcode(),"JSON"};
                List<NameValuePair> paramsList = HttpClientUtil.getParams(params, values);
                JSONObject message = (JSONObject) HttpClientUtil.sendGet("https://restapi.amap.com/v3/weather/weatherInfo?parameters",paramsList);
                LivesResult jsonObject = JSONUtil.jsonToBean(message.toString(), LivesResult.class);
                bean.setCity(jsonObject.getLives().get(0).getCity());
                bean.setHumidity(jsonObject.getLives().get(0).getHumidity());
                bean.setWeather(jsonObject.getLives().get(0).getWeather());
                bean.setTemperature(jsonObject.getLives().get(0).getTemperature());
                bean.setWinddirection(jsonObject.getLives().get(0).getWinddirection());
                bean.setWindpowe(jsonObject.getLives().get(0).getWindpower());
                bean.setReporttime(jsonObject.getLives().get(0).getReporttime());
                baseMapper.updateById(bean);
                //查预报
                Object [] params_for = new Object[]{"key","city","output","extensions"};
                Object [] values_for = new Object[]{key,bean.getAdcode(),"JSON","all"};
                List<NameValuePair> paramsList_for = HttpClientUtil.getParams(params_for, values_for);
                JSONObject message_for = (JSONObject) HttpClientUtil.sendGet("https://restapi.amap.com/v3/weather/weatherInfo?parameters",paramsList_for);
                ForecastsResult jsonObject_for = JSONUtil.jsonToBean(message_for.toString(), ForecastsResult.class);


                weatherinfoForecastsService.remove(new QueryWrapper<WeatherinfoForecasts>().eq("adcode",bean.getAdcode()));
                if(jsonObject_for!=null && jsonObject_for.getForecasts()!=null && jsonObject_for.getForecasts().size()>0){
                    if(jsonObject_for.getForecasts().get(0)!=null && jsonObject_for.getForecasts().get(0).getCasts()!=null && jsonObject_for.getForecasts().get(0).getCasts().size()>0){
                        List<WeatherinfoForecasts> listd = new ArrayList<>();
                        for(Casts casts:jsonObject_for.getForecasts().get(0).getCasts())
                        {
                            WeatherinfoForecasts wf = new WeatherinfoForecasts();
                            wf.setAdcode(bean.getAdcode());
                            wf.setDate(casts.getDate());
                            wf.setWeek(casts.getWeek());
                            wf.setDayweather(casts.getDayweather());
                            wf.setNightweather(casts.getNightweather());
                            wf.setDaytemp(casts.getDaytemp());
                            wf.setNighttemp(casts.getNighttemp());
                            wf.setDaypower(casts.getDaypower());
                            wf.setNightpower(casts.getNightpower());
                            wf.setDaywind(casts.getDaywind());
                            wf.setNightwind(casts.getNightwind());
                            listd.add(wf);
                        }
                        weatherinfoForecastsService.saveBatch(listd);
                    }
                }


            }
        }

    }


}
