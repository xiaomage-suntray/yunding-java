package com.devplatform.admin.modules.sys.service.impl;

import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysLogEntity;
import com.devplatform.admin.modules.sys.dao.SysLogDao;
import com.devplatform.admin.modules.sys.service.SysLogService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

@Service("sysLogService")
public class SysLogServiceImpl extends MyBaseServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        String key = (String)params.get("key");
//
//        IPage<SysLogEntity> page = this.page(
//            new Query<SysLogEntity>(params).getPage(),
//            new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key)
//        );
//
//        return new PageUtils(page);
//    }
}
