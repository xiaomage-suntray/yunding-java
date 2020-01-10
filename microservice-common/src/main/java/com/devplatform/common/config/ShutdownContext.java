package com.devplatform.common.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutdownContext implements ApplicationContextAware
{
	@Autowired
    private static ConfigurableApplicationContext context;

    public static void showdown(){
        if (null != context){
            context.close();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
            context =  (ConfigurableApplicationContext) applicationContext;
    }
}