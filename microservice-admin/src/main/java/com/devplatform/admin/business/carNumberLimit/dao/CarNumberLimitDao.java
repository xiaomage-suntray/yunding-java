package com.devplatform.admin.business.carNumberLimit.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.business.carNumberLimit.bean.CarNumberLimit;
import com.devplatform.common.dao.MyBaseMapper;
/**
 * CarNumberLimit Mapper
 * 用于车辆限行的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface CarNumberLimitDao extends MyBaseMapper<CarNumberLimit> {


    void updateAll();
}
