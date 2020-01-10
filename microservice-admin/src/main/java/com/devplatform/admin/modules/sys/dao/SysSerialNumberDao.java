package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysSerialNumber;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysSerialNumber Mapper
 * 用于code编号表的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysSerialNumberDao extends MyBaseMapper<SysSerialNumber> {
	
	public void insertForAutoIncr(SysSerialNumber bean);
}
