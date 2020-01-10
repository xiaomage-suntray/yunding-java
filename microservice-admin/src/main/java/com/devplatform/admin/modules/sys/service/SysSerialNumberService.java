package com.devplatform.admin.modules.sys.service;

import com.devplatform.common.service.MyBaseService;

import com.devplatform.admin.modules.sys.bean.SysSerialNumber;

/**
 * code编号表的service接口
 * <br>
 * <b>功能：</b>SysSerialNumberService<br>
 * @author 代码生成器产生
 */
public interface SysSerialNumberService extends MyBaseService<SysSerialNumber> {
    public void insertForAutoIncr(SysSerialNumber bean);
}
