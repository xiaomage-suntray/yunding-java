package com.devplatform.admin.business.sysArea.service;

import com.devplatform.common.service.MyBaseService;

import com.devplatform.admin.business.sysArea.bean.SysArea;

import java.util.List;

/**
 * 区域表的service接口
 * <br>
 * <b>功能：</b>SysAreaService<br>
 * @author 代码生成器产生
 */
public interface SysAreaService extends MyBaseService<SysArea> {
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
