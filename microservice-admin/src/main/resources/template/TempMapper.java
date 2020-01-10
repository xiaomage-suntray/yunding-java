package ${bean.pacageName}${bean.modulesName}.dao;
import org.apache.ibatis.annotations.Mapper;

import ${bean.pacageName}${bean.modulesName}.bean.${bean.className};
import com.devplatform.common.dao.MyBaseMapper;
/**
 * ${bean.className} Mapper
 * 用于${bean.tableRemark}的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface ${bean.className}Dao extends MyBaseMapper<${bean.className}> {
	
	
}
