
package com.devplatform.admin.job;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.devplatform.admin.business.sysMessage.service.SysMessageService;

@Component
public class Jobs {
    
    private final static Logger log = LoggerFactory.getLogger(Jobs.class);
    
    /**
     * 一分钟一次执行
     */
    public final static long ONE_Minute = 60 * 1000;
    
    /**
     * 一小时一次执行
     */
    public final static long ONE_Hour = 60 * 60 * 1000;
    
    /**
     * 两小时一次执行
     */
    public final static long Two_Hour = 2 * 60 * 60 * 1000;
    
    @Autowired
    private SysMessageService sysMessageService;

    
    /**
     * 定时任务发送webSocket消息
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月21日上午10:14:37
     */
    @Scheduled(fixedDelay = ONE_Minute)
    public void fixedDelayJob() {
        long start = new Date().getTime();
        log.info("定时发送webSocket消息开始");
        sysMessageService.sendMessages();
        long end = new Date().getTime();
        log.info("定时发送webSocket消息耗时：" + (end - start));
    }
    

    
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        return taskScheduler;
    }
    
}
