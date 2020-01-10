package ${bean.pacageName}${bean.modulesName}.service.impl;

import org.springframework.stereotype.Service;

import ${bean.pacageName}${bean.modulesName}.bean.${bean.className};
import ${bean.pacageName}${bean.modulesName}.dao.${bean.className}Dao;
import ${bean.pacageName}${bean.modulesName}.service.${bean.className}Service;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

import ${bean.pacageName}${bean.modulesName}.bean.${bean.className};

/**
 * ${bean.tableRemark}的service接口实现类
 * <br>
 * <b>功能：</b>${bean.className}ServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("${bean.lowerName}Service")
public class ${bean.className}ServiceImpl extends MyBaseServiceImpl<${bean.className}Dao, ${bean.className}> implements ${bean.className}Service {

}
