
package com.devplatform.admin.modules.sys.service;

import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.common.service.MyBaseService;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:43:39
 */
public interface SysUserService extends MyBaseService<SysUserEntity> {
    
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
     * 保存用户
     * @return 
     */
    @Override
    boolean save(SysUserEntity user);
    
    /**
     * 修改用户
     */
    void update(SysUserEntity user);
    
    /**
     * 删除用户
     */
    void deleteBatch(String[] userIds);
    
    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(String userId, String password, String newPassword);
    
    /**
     * 根据用户id获取部门负责人
     * Description:
     * Company:
     * @author suochaochao
     * 2018年12月27日下午1:32:40
     * @param userId
     * @return
     */
    List<SysUserEntity> queryByOrgHead(String userId);
    
    /**
     * 根据用户id获取上级部门负责人
     * Description:
     * Company:
     * @author suochaochao
     * 2018年12月27日下午1:32:40
     * @param userId
     * @return
     */
    List<SysUserEntity> queryByTheSuperiorOrgHead(String createUserId);
    
    /**
     * 根据组织机构编码获取组织负责人
     * Description:
     * Company:
     * @author suochaochao
     * 2018年12月27日下午2:17:11
     * @param orgCode
     * @return
     */
    List<SysUserEntity> queryByOrgHeadIsOrgCode(String orgCode);
    
    /**
     * 根据角色id获取用户列表
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月7日上午11:13:22
     * @param roleId
     * @return
     */
    List<SysUserEntity> queryUserListByRoleId(String roleId);
    
    /**
     * 获取当前登陆者单位和部门
     * @param user
     * @return
     */
    SysUserEntity infoOrgAndCompany(SysUserEntity user);
    
    /**
     * 根据机构编码获取机构下所有用户
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月13日上午11:11:29
     * @param bean
     * @return
     */
    List<SysUserEntity> queryByOrgCode(SysUserEntity bean);
}
