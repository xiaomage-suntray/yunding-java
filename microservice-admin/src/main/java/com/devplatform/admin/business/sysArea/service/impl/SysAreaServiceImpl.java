package com.devplatform.admin.business.sysArea.service.impl;

import com.devplatform.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devplatform.admin.business.sysArea.bean.SysArea;
import com.devplatform.admin.business.sysArea.dao.SysAreaDao;
import com.devplatform.admin.business.sysArea.service.SysAreaService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

import com.devplatform.admin.business.sysArea.bean.SysArea;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域表的service接口实现类
 * <br>
 * <b>功能：</b>SysAreaServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysAreaService")
public class SysAreaServiceImpl extends MyBaseServiceImpl<SysAreaDao, SysArea> implements SysAreaService {
    @Autowired
    SysAreaDao sysAreaDao;

    /**
     * 区域修改
     * @author heibin
     * 2019年3月22日上午10:10:59
     * @param bean 区域表对象
     * @return
     */
    @Transactional
    @Override
    public void updateSysArea(SysArea bean) {
        sysAreaDao.updateSysArea(bean);

    }
    /**
     * 区域删除
     * @author heibin
     * 2019年3月22日上午10:10:59
     * @param bean 区域表对象
     * @return
     */
    @Transactional
    @Override
    public void deleteSysArea(SysArea bean) {
        if(!StringUtil.isEmpty(bean.getId())){
            String [] ids=bean.getId().split(",");
            //删除商品详情
            for(int i=0;i<ids.length;i++){
                SysArea sysArea=new SysArea();
                sysArea.setId(ids[i]);
                sysArea.setParentId(ids[i]);
                //删除区域表
                sysAreaDao.deleteSysArea(sysArea);
            }

        }

    }
    /**
     * 区域查询
     * @author heibin
     * 2019年3月22日上午10:10:59
     * @param sysArea 区域表对象
     * @return
     */
    @Override
    public List<SysArea> getListByPara(SysArea sysArea) {
        return sysAreaDao.getListByPara(sysArea);
    }
}
