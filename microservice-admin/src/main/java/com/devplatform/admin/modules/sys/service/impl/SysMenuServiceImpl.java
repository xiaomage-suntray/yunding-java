
package com.devplatform.admin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devplatform.admin.modules.sys.bean.SysMenuEntity;
import com.devplatform.admin.modules.sys.dao.SysMenuDao;
import com.devplatform.admin.modules.sys.model.SysMenuModel;
import com.devplatform.admin.modules.sys.service.SysMenuService;
import com.devplatform.admin.modules.sys.service.SysRoleMenuService;
import com.devplatform.admin.modules.sys.service.SysUserService;
import com.devplatform.common.service.impl.MyBaseServiceImpl;
import com.devplatform.common.util.BeanUtil;
import com.devplatform.common.util.Constant;
import com.devplatform.common.util.MapUtils;

@Service("sysMenuService")
public class SysMenuServiceImpl extends MyBaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    
    @Override
    public List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList) {
        List<SysMenuEntity> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }
        
        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }
    
    @Override
    public List<SysMenuEntity> queryListParentId(String parentId) {
        return baseMapper.queryListParentId(parentId);
    }
    
    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }
    
    @Override
    public List<SysMenuModel> getUserMenuList(String userId) {
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            return getAllMenuList(null);
        }
        
        //用户菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }
    
    @Override
    public void delete(String menuId) {
        //删除菜单
        this.removeById(menuId);
        //删除菜单与角色关联
        sysRoleMenuService.removeByMap(new MapUtils().put("menu_id", menuId));
    }
    
    /**
     * 获取所有菜单列表
     */
    private List<SysMenuModel> getAllMenuList(List<String> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId("0", menuIdList);
        //递归获取子菜单
        List<SysMenuModel> subMenuList = getMenuTreeList(menuList, menuIdList);
        
        return subMenuList;
    }
    
    /**
     * 递归
     */
    private List<SysMenuModel> getMenuTreeList(List<SysMenuEntity> menuList, List<String> menuIdList) {
        List<SysMenuModel> subMenuList = new ArrayList<>();
        
        for (SysMenuEntity entity : menuList) {
            SysMenuModel model = BeanUtil.convertBean(entity, SysMenuModel.class);
            //目录
            if (model.getType() == Constant.MenuType.CATALOG.getValue()) {
                model.setList(getMenuTreeList(queryListParentId(model.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(model);
        }
        
        return subMenuList;
    }
    
    //	@Override
    //	public PageUtils queryPage(Map<String, Object> params) {
    //		String name = (String)params.get("name");
    //		IPage<SysMenuEntity> page = this.page(new Query<SysMenuEntity>(params).getPage(), 
    //				new QueryWrapper<SysMenuEntity>().like(StringUtils.isNotBlank(name),"name", name));
    //
    //		return new PageUtils(page);
    //	}
}
