
package com.devplatform.admin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.devplatform.admin.modules.sys.bean.SysOrg;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.modules.sys.bean.SysUserOrg;
import com.devplatform.admin.modules.sys.dao.SysUserDao;
import com.devplatform.admin.modules.sys.service.SysOrgService;
import com.devplatform.admin.modules.sys.service.SysRoleService;
import com.devplatform.admin.modules.sys.service.SysUserOrgService;
import com.devplatform.admin.modules.sys.service.SysUserPostService;
import com.devplatform.admin.modules.sys.service.SysUserRoleService;
import com.devplatform.admin.modules.sys.service.SysUserService;
import com.devplatform.common.base.exception.RRException;
import com.devplatform.common.service.impl.MyBaseServiceImpl;
import com.devplatform.common.util.Constant;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends MyBaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    
    @Autowired
    private SysUserRoleService sysUserRoleService;
    
    @Autowired
    private SysRoleService sysRoleService;
    
    @Autowired
    private SysUserOrgService sysUserOrgService;
    
    @Autowired
    private SysUserPostService sysUserPostService;
    
    @Autowired
    private SysOrgService sysOrgService;
    
    //	@Override
    //	public PageUtils queryPage(Map<String, Object> params) {
    //		String username = (String)params.get("username");
    //		String createUserId = (String)params.get("createUserId");
    //
    //		QueryWrapper<SysUserEntity> queryWrapper = new  QueryWrapper<SysUserEntity>();
    //		queryWrapper.like(StringUtils.isNotBlank(username),"username", username);
    //		queryWrapper.eq(createUserId != null,"create_user_id", createUserId);
    //		
    //		IPage<SysUserEntity> page = this.page(
    //			new Query<SysUserEntity>(params).getPage(),
    //			queryWrapper
    //		);
    //
    //		return new PageUtils(page);
    //	}
    
    @Override
    public List<String> queryAllPerms(String userId) {
        return baseMapper.queryAllPerms(userId);
    }
    
    @Override
    public List<String> queryAllMenuId(String userId) {
        return baseMapper.queryAllMenuId(userId);
    }
    
    @Override
    public SysUserEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }
    
    @Override
    @Transactional
    public boolean save(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        super.save(user);
        //TODO: 对新增用户越权的检查，此处限制过于死板，eg:如果创建角色的人离职了，那么这些角色除了管理员其他人都不能用了。
        //检查角色是否越权
        //		checkRole(user);
        
        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
        //保存用户与岗位关系
        sysUserPostService.saveOrUpdate(user.getUserId(), user.getPostIdList());
        //保存用户与组织机构关系
        sysUserOrgService.saveOrUpdate(user.getUserId(), user.getOrgIdList());
        return true;
    }
    
    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        }
        else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);
        
        //		//检查角色是否越权
        //		checkRole(user);
        
        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
        //保存用户与岗位关系
        sysUserPostService.saveOrUpdate(user.getUserId(), user.getPostIdList());
        //保存用户与组织机构关系
        sysUserOrgService.saveOrUpdate(user.getUserId(), user.getOrgIdList());
    }
    
    @Override
    public void deleteBatch(String[] userId) {
        this.removeByIds(Arrays.asList(userId));
    }
    
    @Override
    public boolean updatePassword(String userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity, new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }
    
    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user) {
        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId().equals(Constant.SUPER_ADMIN)) {
            return;
        }
        
        //查询用户创建的角色列表
        List<String> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
        
        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new RRException("新增用户所选角色，不是本人创建");
        }
    }
    
    @Override
    public List<SysUserEntity> queryByOrgHead(String userId) {
        // TODO Auto-generated method stub
        return baseMapper.queryByOrgHead(userId);
    }
    
    @Override
    public List<SysUserEntity> queryByTheSuperiorOrgHead(String createUserId) {
        // TODO Auto-generated method stub
        return baseMapper.queryByTheSuperiorOrgHead();
    }
    
    @Override
    public List<SysUserEntity> queryByOrgHeadIsOrgCode(String orgCode) {
        // TODO Auto-generated method stub
        return baseMapper.queryByOrgHeadIsOrgCode(orgCode);
    }
    
    /**
     * 根据角色id获取用户列表
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月7日上午11:14:17
     * @param roleId
     * @return
     */
    @Override
    public List<SysUserEntity> queryUserListByRoleId(String roleId) {
        // TODO Auto-generated method stub
        return baseMapper.queryUserListByRoleId(roleId);
    }
    
    @Override
    public SysUserEntity infoOrgAndCompany(SysUserEntity user) {
        //缓存用户的组织机构信息
        List<SysUserOrg> userOrgList = sysUserOrgService.list(new QueryWrapper<SysUserOrg>().eq("user_id", user.getUserId()));
        List<String> orgIdList = new ArrayList<String>();
        List<String> orgNameList = new ArrayList<String>();
        for (SysUserOrg userOrg : userOrgList) {
            //获取当前用户所在公司
            if (null != userOrg.getOrgCode() && !userOrg.getOrgCode().equals("")) {
                //获取用户所在组织结构
                SysOrg orgd = sysOrgService.getOne(new QueryWrapper<SysOrg>().eq("org_code", userOrg.getOrgCode()));
                orgNameList.add(orgd.getOrgName());
                //判断用户所在组织结构是否为公司
                if (orgd.getIsCorporation().equals("1")) {
                    user.setOrgName(orgd.getOrgName());
                    user.setOrgCode(orgd.getOrgCode());
                }
                else {
                    
                    String[] codeArry = userOrg.getOrgCode().split("_");
                    List<SysOrg> orgList = sysOrgService.list(new QueryWrapper<SysOrg>().like("org_code", codeArry[0]));
                    String code = userOrg.getOrgCode().substring(0, userOrg.getOrgCode().lastIndexOf("_"));
                    boolean boo = false;
                    for (int i = 0; i < codeArry.length; i++) {
                        for (SysOrg org : orgList) {
                            if (org.getOrgCode().equals(code) && org.getIsCorporation().equals("1")) {
                                user.setOrgName(org.getOrgName());
                                user.setOrgCode(org.getOrgCode());
                                user.setIsCorporation(org.getIsCorporation());
                                user.setCorporationId(org.getOrgId());
                                boo = true;
                                break;
                            }
                        }
                        if (boo) {
                            break;
                        }
                        else {
                            code = code.substring(0, code.lastIndexOf("_"));
                        }
                    }
                }
            }
            orgIdList.add(userOrg.getOrgId() + "#" + userOrg.getOrgCode());
        }
        user.setOrgIdList(orgIdList);
        user.setOrgNameList(orgNameList);
        return user;
    }
    
    /**
     * 根据机构编码获取机构下所有用户
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月13日上午11:12:16
     * @param bean
     * @return
     */
    @Override
    public List<SysUserEntity> queryByOrgCode(SysUserEntity bean) {
        return baseMapper.queryByOrgCode(bean);
    }
}
