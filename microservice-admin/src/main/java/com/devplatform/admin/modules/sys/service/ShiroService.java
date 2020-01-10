package com.devplatform.admin.modules.sys.service;

import java.util.Set;

import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.modules.sys.bean.SysUserTokenEntity;

/**
 * shiro相关接口
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-06-06 8:49
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(String userId);

    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity queryUser(String userId);
}
