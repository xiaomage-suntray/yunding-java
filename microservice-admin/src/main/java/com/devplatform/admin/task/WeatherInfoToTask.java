package com.devplatform.admin.task;

import com.devplatform.admin.business.weatherInfo.service.WeatherinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
/**
 *  文章内容拆分
 */
public class WeatherInfoToTask {


    private final static Logger log = LoggerFactory.getLogger(WeatherInfoToTask.class);


    @Autowired
    private WeatherinfoService weatherinfoService;

    /**
     * 默认俩小时执行一次
     */
    private final static String DEFAULT_TASK_TIME = "0 0 0/2 * * ?";


    /**
     * 爬虫附件，附件地址拆分
     *
     * @return
     */
    @Scheduled(cron = DEFAULT_TASK_TIME)
    @Async
    public void fileToTask() {

        try {
            weatherinfoService.getGaodeWeathrinfo();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("定时任务出现异常",e);
        } finally {
        }
    }


}
