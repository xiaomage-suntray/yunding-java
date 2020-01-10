package com.devplatform.admin.modules.sys.service;


import java.util.Map;

import com.devplatform.admin.modules.sys.bean.SysConfigEntity;
import com.devplatform.common.service.MyBaseService;
import com.devplatform.common.util.PageUtils;

/**
 * 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:49:01
 */
public interface SysConfigService extends MyBaseService<SysConfigEntity>  {

	/**
	 * 保存配置信息
	 * @return 
	 */
	public boolean save(SysConfigEntity config);
	
	/**
	 * 更新配置信息
	 */
	public void update(SysConfigEntity config);
	
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(String[] ids);
	
	/**
	 * 根据key，获取配置的value值
	 * 
	 * @param key           key
	 */
	public String getValue(String key);
	
	/**
	 * 根据key，获取value的Object对象
	 * @param key    key
	 * @param clazz  Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);
	
}
