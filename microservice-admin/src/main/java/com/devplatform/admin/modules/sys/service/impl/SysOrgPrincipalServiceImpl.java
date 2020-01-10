package com.devplatform.admin.modules.sys.service.impl;

import com.devplatform.common.util.MapUtils;
import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysOrgPrincipal;
import com.devplatform.admin.modules.sys.dao.SysOrgPrincipalDao;
import com.devplatform.admin.modules.sys.service.SysOrgPrincipalService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 组织机构与负责人关联关系表的service接口实现类
 * <br>
 * <b>功能：</b>SysOrgPrincipalServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysOrgPrincipalService")
public class SysOrgPrincipalServiceImpl extends MyBaseServiceImpl<SysOrgPrincipalDao, SysOrgPrincipal> implements SysOrgPrincipalService {

    @Override
    public void saveOrUpdateOrgRel(String orgId, List<String> userIdList, String createUserId) {
        //先删除组织机构与负责人的关系
        this.removeByMap(new MapUtils().put("org_id", orgId));

        if(userIdList == null || userIdList.size() == 0){
            return ;
        }

        //保存组织机构与负责人的关系
        List<SysOrgPrincipal> list = new ArrayList<>(userIdList.size());
        for(String userId : userIdList){
            SysOrgPrincipal sysOrgPrincipal = new SysOrgPrincipal();
            sysOrgPrincipal.setOrgId(orgId);
            sysOrgPrincipal.setUserId(userId);
            sysOrgPrincipal.setCreateTime(new Date());
            sysOrgPrincipal.setCreateUserId(createUserId);
            list.add(sysOrgPrincipal);
        }
        this.saveBatch(list);
    }
}
