package com.${bean.pacageName}.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.${bean.pacageName}.bean.${bean.classOracleName};
/**
 * ${bean.className} Dao
 * 用于：${bean.tableName} 表操作
 * @author 代码生成器产生
 *
 */
public interface ${bean.classOracleName}Dao {
	/**
	 * 列表查询
	 * @param ${bean.classOracleName}
	 * @return List<${bean.classOracleName}>
	 */
	public List<${bean.classOracleName}> getList(@Param("bean") ${bean.classOracleName} bean);
    /**
     * 添加方法
     * @param ${bean.classOracleName}
     * @return Integer
     */
	public Integer save(@Param("bean") ${bean.classOracleName} bean);
    /**
     * 修改方法
     * @param ${bean.classOracleName}
     * @return Integer
     */
	public Integer update(@Param("bean") ${bean.classOracleName} bean);
    /**
     * 根据id获取单条数据
     * @param id
     * @return ${bean.classOracleName}
     */
	public ${bean.classOracleName} getById(@Param("id") String id);
     /**
      * 获取单条数据
      * @param id
      * @return Integer
      */
	public Integer delete(@Param("id") String id);
	
}
