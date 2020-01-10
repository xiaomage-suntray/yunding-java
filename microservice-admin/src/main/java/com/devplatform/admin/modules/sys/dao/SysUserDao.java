
package com.devplatform.admin.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.common.dao.MyBaseMapper;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
@Mapper
public interface SysUserDao extends MyBaseMapper<SysUserEntity> {
    
    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(String userId);
    
    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);
    
    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);
    
    /**
     * 查询当前用户部门负责人
     * Description:
     * Company:
     * @author suochaochao
     * 2018年12月27日下午1:33:25
     * @param userId
     * @return
     */
    List<SysUserEntity> queryByOrgHead(String userId);
    
    /**
     * 查询当前用户上级部门负责人
     * Description:
     * Company:
     * @author suochaochao
     * 2018年12月27日下午1:33:25
     * @param userId
     * @return
     */
    List<SysUserEntity> queryByTheSuperiorOrgHead();
    
    /**
     * 根据组织机构编码获取组织负责人
     * Description:
     * Company:
     * @author suochaochao
     * 2018年12月27日下午2:17:58
     * @param orgCode
     * @return
     */
    List<SysUserEntity> queryByOrgHeadIsOrgCode(String orgCode);
    
    /**
     *  保存供应商用户
     * Description:
     * Company:
     * @author heibin
     * 2019年2月26日下午2:17:58
     * @param user
     * @return
     */
    void saveSupplierUser(SysUserEntity user);
    
    /**
     *   获取供应商用户数量
     * Description:
     * Company:
     * @author heibin
     * 2019年2月26日下午2:17:58
     * @param u
     * @return int
     */
    int selectSupplierUserCount(SysUserEntity u);
    
    /**
     * 根据角色id获取用户列表
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月7日上午11:14:32
     * @param roleId
     * @return
     */
    List<SysUserEntity> queryUserListByRoleId(String roleId);
    
    /**
     * 根据姓名，查询系统用户
     */
    List<SysUserEntity> queryByThename(SysUserEntity sysUserEntity);
    
    /**
     * 根据机构编码获取机构下所有用户
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月13日上午11:13:06
     * @param bean
     * @return
     */
    List<SysUserEntity> queryByOrgCode(SysUserEntity bean);
}
