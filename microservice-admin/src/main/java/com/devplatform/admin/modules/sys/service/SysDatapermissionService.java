package com.devplatform.admin.modules.sys.service;

import com.devplatform.common.service.MyBaseService;

import com.devplatform.admin.modules.sys.bean.SysDatapermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据权限表的service接口
 * <br>
 * <b>功能：</b>SysDatapermissionService<br>
 * @author 代码生成器产生
 */
public interface SysDatapermissionService extends MyBaseService<SysDatapermission> {
    public List<SysDatapermission> queryListByUserId(@Param("userId") String userId);
}
