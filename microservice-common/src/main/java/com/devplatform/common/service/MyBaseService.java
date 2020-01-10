package com.devplatform.common.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.devplatform.common.util.PageUtils;
/**
 * @Title: 腾信微服务快速开发平台
 * @Description: Service接口基础类
 * @Copyright: Copyright (c) 2018
 * @Company: 北京腾信软创科技股份有限公司
 * @author Rice
 * @version 1.1  2018年11月8日
 */
public interface MyBaseService<T> extends IService<T> {

	/**
	 * 分页查询
	 * @param params
	 * @return PageUtils
	 * @throws
	 * @author Rice
	 * @date 2018年11月12日
	 */
	PageUtils queryPage(Map<String, Object> params,Wrapper<T> updateWrapper);
}
