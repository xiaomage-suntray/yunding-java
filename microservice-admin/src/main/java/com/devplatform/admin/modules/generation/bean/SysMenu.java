package com.devplatform.admin.modules.generation.bean;

import java.util.ArrayList;
import java.util.List;

public class SysMenu {
    
    private String id;// 主键
    
    private String name;// 菜单名称
    
    private String url;// 系统url
    
    private String parentId;// 父id 关联sys_menu.id
    
    private Integer deleted;// 是否删除,0=未删除，1=已删除
    
    private java.sql.Timestamp createTime;// 创建时间
    
    private java.sql.Timestamp updateTime;// 修改时间
    
    private Integer rank;// 排序
    
    private String actions; // 注册Action 按钮|分隔
    
    private int subCount;// 子菜单总数
    
    private Integer isopen;//是否弹出新页面 0默认 不弹出  ，1弹出
    
    // 菜单按钮
    private List<SysMenuBtn> btns;
    
    private String icon;// 菜单图标
    
    private String remark;//备注
    
    //以下是非数据库字段
    private List<SysMenu> children;//子菜单
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getParentId() {
        return parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public Integer getDeleted() {
        return deleted;
    }
    
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
    
    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    
    public Integer getRank() {
        return rank;
    }
    
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    
    public List<SysMenuBtn> getBtns() {
        return btns;
    }
    
    public void setBtns(List<SysMenuBtn> btns) {
        this.btns = btns;
    }
    
    public String getActions() {
        return actions;
    }
    
    public void setActions(String actions) {
        this.actions = actions;
    }
    
    public int getSubCount() {
        return subCount;
    }
    
    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public List<SysMenu> getChildren() {
        if (children == null) {
            children = new ArrayList();
        }
        return children;
    }
    
    public void setCildren(List<SysMenu> children) {
        this.children = children;
    }
    
    public Integer getIsopen() {
        return isopen;
    }
    
    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
