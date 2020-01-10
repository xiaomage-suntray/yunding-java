package com.devplatform.admin.business.sysCity.dao;

import com.devplatform.admin.business.sysCity.bean.SysCity;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysCity Mapper
 * 用于城市表的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysCityDao extends MyBaseMapper<SysCity> {
	
	
}
