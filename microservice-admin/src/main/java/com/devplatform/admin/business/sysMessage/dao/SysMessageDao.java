
package com.devplatform.admin.business.sysMessage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.business.sysMessage.bean.SysMessage;
import com.devplatform.admin.business.sysMessage.model.SysMessageModel;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * SysMessage Mapper
 * 用于消息模块的数据库操作
 * @author Administrator
 *
 */
@Mapper
public interface SysMessageDao extends MyBaseMapper<SysMessage> {
    
    /**
     * 条件查询
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月12日下午3:51:03
     * @param model
     * @return
     */
    List<SysMessage> queryByList(SysMessageModel model);
    
    /**
     * 条件查询  带sys_user表
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月12日下午3:51:03
     * @param model
     * @return
     */
    List<SysMessage> queryByListByUser(SysMessageModel model);
    
}
