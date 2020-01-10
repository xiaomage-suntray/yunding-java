package com.devplatform.admin.modules.sys.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysPost;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * SysPost Mapper
 * 用于岗位管理的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysPostDao extends MyBaseMapper<SysPost> {
	
	
}
