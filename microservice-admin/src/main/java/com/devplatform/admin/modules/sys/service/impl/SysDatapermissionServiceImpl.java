package com.devplatform.admin.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysDatapermission;
import com.devplatform.admin.modules.sys.dao.SysDatapermissionDao;
import com.devplatform.admin.modules.sys.service.SysDatapermissionService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

import com.devplatform.admin.modules.sys.bean.SysDatapermission;

import java.util.List;

/**
 * 数据权限表的service接口实现类
 * <br>
 * <b>功能：</b>SysDatapermissionServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysDatapermissionService")
public class SysDatapermissionServiceImpl extends MyBaseServiceImpl<SysDatapermissionDao, SysDatapermission> implements SysDatapermissionService {

    @Autowired
    private SysDatapermissionDao sysDatapermissionDao;

    @Override
    public List<SysDatapermission> queryListByUserId(String userId) {
        return sysDatapermissionDao.queryListByUserId(userId);
    }
}
