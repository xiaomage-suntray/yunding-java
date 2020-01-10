package com.devplatform.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devplatform.common.dao.MyBaseMapper;
import com.devplatform.common.service.MyBaseService;
import com.devplatform.common.util.PageUtils;
import com.devplatform.common.util.Query;
/**
 * @Title: 腾信微服务快速开发平台
 * @Description: Service实现基础类
 * @Copyright: Copyright (c) 2018
 * @Company: 北京腾信软创科技股份有限公司
 * @author Rice
 * @version 1.1  2018年11月8日
 */
public class MyBaseServiceImpl<M extends MyBaseMapper<T>, T> 
			extends ServiceImpl<MyBaseMapper<T>, T>  
			implements MyBaseService<T>{

	@Autowired
    protected M baseMapper;
	
	@Override
	public PageUtils queryPage(Map<String, Object> params,Wrapper<T> updateWrapper) {
		IPage<T> page = this.page(new Query<T>(params).getPage(),updateWrapper);
		return new PageUtils(page);
	}
}
