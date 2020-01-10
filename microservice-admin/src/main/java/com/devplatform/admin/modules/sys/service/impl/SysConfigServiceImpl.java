package com.devplatform.admin.modules.sys.service.impl;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devplatform.admin.modules.sys.bean.SysConfigEntity;
import com.devplatform.admin.modules.sys.dao.SysConfigDao;
import com.devplatform.admin.modules.sys.redis.SysConfigRedis;
import com.devplatform.admin.modules.sys.service.SysConfigService;
import com.devplatform.common.base.exception.RRException;
import com.devplatform.common.service.impl.MyBaseServiceImpl;
import com.google.gson.Gson;

@Service("sysConfigService")
public class SysConfigServiceImpl extends MyBaseServiceImpl<SysConfigDao,SysConfigEntity> implements SysConfigService {
	@Autowired
	private SysConfigRedis sysConfigRedis;

//	@Override
//	public PageUtils queryPage(Map<String, Object> params) {
//		String paramKey = (String)params.get("paramKey");
//
//		IPage<SysConfigEntity> page = this.page(
//				new Query<SysConfigEntity>(params).getPage(),
//				new QueryWrapper<SysConfigEntity>()
//					.like(StringUtils.isNotBlank(paramKey),"param_key", paramKey)
//					.eq("status", 1)
//		);
//
//		return new PageUtils(page);
//	}
	
	@Override
	public boolean save(SysConfigEntity config) {
		this.save(config);
		sysConfigRedis.saveOrUpdate(config);
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysConfigEntity config) {
		this.updateById(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateValueByKey(String key, String value) {
		baseMapper.updateValueByKey(key, value);
		sysConfigRedis.delete(key);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(String[] ids) {
		for(String id : ids){
			SysConfigEntity config = this.getById(id);
			sysConfigRedis.delete(config.getParamKey());
		}
		this.removeByIds(Arrays.asList(ids));
	}

	@Override
	public String getValue(String key) {
		SysConfigEntity config = sysConfigRedis.get(key);
		if(config == null){
			config = baseMapper.queryByKey(key);
			sysConfigRedis.saveOrUpdate(config);
		}

		return config == null ? null : config.getParamValue();
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key);
		if(StringUtils.isNotBlank(value)){
			return new Gson().fromJson(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RRException("获取参数失败");
		}
	}
}
