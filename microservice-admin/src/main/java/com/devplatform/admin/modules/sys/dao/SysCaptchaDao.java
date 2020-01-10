package com.devplatform.admin.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;


import com.devplatform.admin.modules.sys.bean.SysCaptchaEntity;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-02-10
 */
@Mapper
public interface SysCaptchaDao extends MyBaseMapper<SysCaptchaEntity> {

}
