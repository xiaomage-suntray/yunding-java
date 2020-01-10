package com.devplatform.admin.business.sysArea.dao;
import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.business.sysArea.bean.SysArea;
import com.devplatform.common.dao.MyBaseMapper;

import java.util.List;

/**
 * SysArea Mapper
 * 用于区域表的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysAreaDao extends MyBaseMapper<SysArea> {

    /**
     * 区域修改
     * @author heibin
     * 2019年3月22日上午10:10:59
     * @param bean 区域表对象
     * @return
     */
    void updateSysArea(SysArea bean);
    /**
     * 区域删除
     * @author heibin
     * 2019年3月22日上午10:10:59
     * @param bean 区域表对象
     * @return
     */
    void deleteSysArea(SysArea bean);
    /**
     * 区域查询
     * @author heibin
     * 2019年3月22日上午10:10:59
     * @param sysArea 区域表对象
     * @return
     */
    List<SysArea> getListByPara(SysArea sysArea);
}
