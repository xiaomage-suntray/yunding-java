package com.devplatform.admin.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;


import com.devplatform.admin.modules.sys.bean.SysUserTokenEntity;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * 系统用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface SysUserTokenDao extends MyBaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
