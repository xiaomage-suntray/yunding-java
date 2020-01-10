package com.devplatform.common.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * @Title: 腾信微服务快速开发平台
 * @Description: 通用mapper基础类
 * @Copyright: Copyright (c) 2018
 * @Company: 北京腾信软创科技股份有限公司
 * @author Rice
 * @version 1.1  2018年11月8日
 */
@Mapper
public interface MyBaseMapper<T> extends BaseMapper<T> {

	
}
