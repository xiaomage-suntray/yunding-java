package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.Demo;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * Demo Mapper
 * 用于demo演示权限的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface DemoDao extends MyBaseMapper<Demo> {
	
	
}
