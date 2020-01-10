package com.devplatform.admin.modules.sys.service;

import com.devplatform.admin.modules.sys.bean.SysDictionary;
import com.devplatform.common.service.MyBaseService;

import java.util.List;

/**
 * 字典表的service接口
 * <br>
 * <b>功能：</b>SysDictionaryService<br>
 * @author 代码生成器产生
 */
public interface SysDictionaryService extends MyBaseService<SysDictionary> {
    /**
     * 获取满足查询条件的字典列表
     * @param sysDictionary 承接对象
     * @return
     */
    List<SysDictionary> getListByPara(SysDictionary sysDictionary);
}
