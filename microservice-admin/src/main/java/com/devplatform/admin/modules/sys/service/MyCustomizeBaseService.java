package com.devplatform.admin.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.common.util.PageUtils;

import java.util.Map;

/**
 * @description: 自定义的baseService，最初是为了处理权限过滤的问题
 * @author: wsh
 * @create: 2019-01-07 14:30
 **/
public interface MyCustomizeBaseService<T> extends IService<T> {
    PageUtils queryPage(Map<String, Object> params, QueryWrapper<T> queryWrapper, SysUserEntity userEntity);
}
