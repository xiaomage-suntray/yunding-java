package com.devplatform.admin.modules.sys.service.impl;

import com.devplatform.admin.modules.sys.bean.SysDictionary;
import com.devplatform.admin.modules.sys.dao.SysDictionaryDao;
import com.devplatform.admin.modules.sys.service.SysDictionaryService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表的service接口实现类
 * <br>
 * <b>功能：</b>SysDictionaryServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysDictionaryService")
public class SysDictionaryServiceImpl extends MyBaseServiceImpl<SysDictionaryDao, SysDictionary> implements SysDictionaryService {
    @Autowired
    SysDictionaryDao sysDictionaryDao;
    /**
     * 获取满足查询条件的字典列表
     * @param sysDictionary 承接对象
     * @return
     */
    @Override
    public List<SysDictionary> getListByPara(SysDictionary sysDictionary) {
        return sysDictionaryDao.getListByPara(sysDictionary);
    }
}
