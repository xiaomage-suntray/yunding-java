package com.devplatform.admin.modules.sys.dao;


import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysLogEntity;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * 系统日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-08 10:40:56
 */
@Mapper
public interface SysLogDao extends MyBaseMapper<SysLogEntity> {
	
}
