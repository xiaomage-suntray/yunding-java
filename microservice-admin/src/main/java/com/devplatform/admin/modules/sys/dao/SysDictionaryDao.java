package com.devplatform.admin.modules.sys.dao;

import com.devplatform.admin.modules.sys.bean.SysDictionary;
import com.devplatform.common.dao.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysDictionary Mapper
 * 用于字典表的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysDictionaryDao extends MyBaseMapper<SysDictionary> {
    /**
     * 获取满足查询条件的字典列表
     * @param sysDictionary 承接对象
     * @return
     */
    List<SysDictionary> getListByPara(SysDictionary sysDictionary);
}
