package com.devplatform.admin.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysSerialNumber;
import com.devplatform.admin.modules.sys.dao.SysSerialNumberDao;
import com.devplatform.admin.modules.sys.service.SysSerialNumberService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

import com.devplatform.admin.modules.sys.bean.SysSerialNumber;

/**
 * code编号表的service接口实现类
 * <br>
 * <b>功能：</b>SysSerialNumberServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysSerialNumberService")
public class SysSerialNumberServiceImpl extends MyBaseServiceImpl<SysSerialNumberDao, SysSerialNumber> implements SysSerialNumberService {

    @Autowired
    private SysSerialNumberDao sysSerialNumberDao;

    public void insertForAutoIncr(SysSerialNumber bean){
        sysSerialNumberDao.insertForAutoIncr(bean);
    }
}
