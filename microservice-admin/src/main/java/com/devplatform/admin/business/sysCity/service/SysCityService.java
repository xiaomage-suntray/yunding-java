package com.devplatform.admin.business.sysCity.service;


import com.devplatform.admin.business.sysCity.bean.SysCity;
import com.devplatform.common.service.MyBaseService;
import org.springframework.web.multipart.MultipartFile;


/**
 * 城市表的service接口
 * <br>
 * <b>功能：</b>SysCityService<br>
 * @author 代码生成器产生
 */
public interface SysCityService extends MyBaseService<SysCity> {


    void importCityInfoExcel(MultipartFile file) throws Exception;
}
