package com.devplatform.admin.business.sysFile.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.business.sysFile.bean.SysFile;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysFile Mapper
 * 用于系统文件表的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysFileDao extends MyBaseMapper<SysFile> {
	
	
}
