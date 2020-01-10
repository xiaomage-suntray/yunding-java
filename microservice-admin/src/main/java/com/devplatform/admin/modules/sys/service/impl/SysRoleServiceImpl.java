package com.devplatform.admin.modules.sys.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.devplatform.admin.modules.sys.bean.SysUserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devplatform.admin.modules.sys.bean.SysRoleEntity;
import com.devplatform.admin.modules.sys.dao.SysRoleDao;
import com.devplatform.admin.modules.sys.service.SysRoleMenuService;
import com.devplatform.admin.modules.sys.service.SysRoleService;
import com.devplatform.admin.modules.sys.service.SysUserRoleService;
import com.devplatform.admin.modules.sys.service.SysUserService;
import com.devplatform.common.base.exception.RRException;
import com.devplatform.common.service.impl.MyBaseServiceImpl;
import com.devplatform.common.util.Constant;

/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends MyBaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    SysRoleDao sysRoleDao;

//	@Override
//	public PageUtils queryPage(Map<String, Object> params) {
//		String roleName = (String)params.get("roleName");
//		String createUserId = (String)params.get("createUserId");
//
//		IPage<SysRoleEntity> page = this.page(
//			new Query<SysRoleEntity>(params).getPage(),
//			new QueryWrapper<SysRoleEntity>()
//				.like(StringUtils.isNotBlank(roleName),"role_name", roleName)
//				.eq(createUserId != null,"create_user_id", createUserId)
//		);
//
//		return new PageUtils(page);
//	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveMenu(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
		return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleEntity role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }


    @Override
	public List<String> queryRoleIdList(String createUserId) {
		return baseMapper.queryRoleIdList(createUserId);
	}
    /**
     * TODO 查询当前登陆人的角色列表  id  name  供应商评分角色使用
     *
     * @return List<SysRoleEntity>
     * @Param  sysUserRoleEntity
     * @Author heibin
     * @Date 2019/4/9 13:49
     **/
    @Override
    public List<SysRoleEntity> sysRoleList(SysUserRoleEntity sysUserRoleEntity) {
        return sysRoleDao.sysRoleList(sysUserRoleEntity);
    }

    /**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleEntity role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId() .equals(Constant.SUPER_ADMIN)){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<String> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new RRException("新增角色的权限，已超出你的权限范围");
		}
	}
}
