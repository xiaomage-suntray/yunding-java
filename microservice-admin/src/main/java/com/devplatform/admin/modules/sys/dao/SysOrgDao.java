
package com.devplatform.admin.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysOrg;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * SysOrg Mapper
 * 用于组织机构管理的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysOrgDao extends MyBaseMapper<SysOrg> {
    
    List<SysOrg> findAllSysOrg(SysOrg sysOrg);
    
    /**
     * 获取所有公司
     * Description:
     * Company:
     * @author suochaochao
     * 2019年4月11日上午10:19:43
     * @return
     */
    List<SysOrg> queryListByCompany();
}
