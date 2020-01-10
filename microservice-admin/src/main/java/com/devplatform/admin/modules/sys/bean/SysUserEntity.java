
package com.devplatform.admin.modules.sys.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:55
 */
@ApiModel(value = "用户对象", description = "用户对象user")
@TableName("sys_user")
public class SysUserEntity implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3559046249781460649L;
    
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "主键ID", name = "userId")
    @TableId
    private String userId;
    
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username")
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    /**
     * 姓名  
     */
    @ApiModelProperty(value = "姓名", name = "thename")
    @NotBlank(message = "姓名不能为空")
    private String thename;
    
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    @NotBlank(message = "密码不能为空")
    private String password;
    
    /**
     * 盐
     */
    @ApiModelProperty(value = "盐", name = "salt")
    private String salt;
    
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "email")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "mobile")
    private String mobile;
    
    /**
     *  用户类型
     */
    @ApiModelProperty(value = "用户类型", name = "type")
    private String type;
    
    /**
     *  是否专家
     */
    @ApiModelProperty(value = "是否专家", name = "isExperts")
    private Integer isExperts;//1否，2是
    
    /**
     * 状态  0：禁用   1：正常
     */
    @ApiModelProperty(value = "状态(0：禁用   1：正常)", allowableValues = "0,1", name = "status")
    private Integer status;
    
    @ApiModelProperty(value = "微信openid", name = "openid")
    private String openid;
    
    /**
     * 角色ID列表
     */
    @TableField(exist = false)
    private List<String> roleIdList;
    
    @TableField(exist = false)
    private java.lang.String isCorporation;//是否公司
    
    public String getIsCorporation() {
        return isCorporation;
    }
    
    public void setIsCorporation(String isCorporation) {
        this.isCorporation = isCorporation;
    }
    
    /**
     * 岗位ID列表
     */
    @TableField(exist = false)
    private List<String> postIdList;
    
    /**
     * 组织机构的ID列表
     */
    @TableField(exist = false)
    private List<String> orgIdList;
    
    /**
     * 组织机构名称
     */
    @TableField(exist = false)
    private List<String> orgNameList;
    
    /**
     * 公司名称
     */
    @TableField(exist = false)
    private String orgName;
    
    /**
     * 公司编号
     */
    @TableField(exist = false)
    private String orgCode;
    
    /**
     * 本次登录选择的组织机构Id
     */
    @TableField(exist = false)
    private String currentChoseOrgId;
    
    /**
     * 本次登录选择的组织机构Code
     */
    @TableField(exist = false)
    private String currentChoseOrgCode;
    
    /**
     * 数据权限过滤数组
     */
    @TableField(exist = false)
    private List<String> dataPermissionStr;
    
    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID", name = "createUserId")
    private String createUserId;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
    
    /**
     * 所属公司ID
     */
    @TableField(exist = false)
    private String corporationId;
    
    /**
     * 设置：
     * @param userId 
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 获取：
     * @return String
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * 设置：用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * 获取：用户名
     * @return String
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * 设置：密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 获取：密码
     * @return String
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 设置：邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * 获取：邮箱
     * @return String
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * 设置：手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    /**
     * 获取：手机号
     * @return String
     */
    public String getMobile() {
        return mobile;
    }
    
    /**
     * 设置：状态  0：禁用   1：正常
     * @param status 状态  0：禁用   1：正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * 获取：状态  0：禁用   1：正常
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * 设置：创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * 获取：创建时间
     * @return Date
     */
    public Date getCreateTime() {
        return createTime;
    }
    
    public List<String> getRoleIdList() {
        return roleIdList;
    }
    
    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }
    
    public List<String> getPostIdList() {
        return postIdList;
    }
    
    public void setPostIdList(List<String> postIdList) {
        this.postIdList = postIdList;
    }
    
    public List<String> getOrgIdList() {
        return orgIdList;
    }
    
    public void setOrgIdList(List<String> orgIdList) {
        this.orgIdList = orgIdList;
    }
    
    public String getCurrentChoseOrgId() {
        return currentChoseOrgId;
    }
    
    public void setCurrentChoseOrgId(String currentChoseOrgId) {
        this.currentChoseOrgId = currentChoseOrgId;
    }
    
    public String getCurrentChoseOrgCode() {
        return currentChoseOrgCode;
    }
    
    public void setCurrentChoseOrgCode(String currentChoseOrgCode) {
        this.currentChoseOrgCode = currentChoseOrgCode;
    }
    
    public List<String> getDataPermissionStr() {
        return dataPermissionStr;
    }
    
    public void setDataPermissionStr(List<String> dataPermissionStr) {
        this.dataPermissionStr = dataPermissionStr;
    }
    
    public String getCreateUserId() {
        return createUserId;
    }
    
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getThename() {
        return thename;
    }
    
    public void setThename(String thename) {
        this.thename = thename;
    }
    
    public List<String> getOrgNameList() {
        return orgNameList;
    }
    
    public void setOrgNameList(List<String> orgNameList) {
        this.orgNameList = orgNameList;
    }
    
    public String getOrgName() {
        return orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    
    public String getOrgCode() {
        return orgCode;
    }
    
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getCorporationId() {
        return corporationId;
    }
    
    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }
    
    public Integer getIsExperts() {
        return isExperts;
    }
    
    public void setIsExperts(Integer isExperts) {
        this.isExperts = isExperts;
    }
    
    public String getOpenid() {
        return openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    @Override
    public String toString() {
        return "SysUserEntity{" + "userId='" + userId + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", salt='" + salt
                + '\'' + ", email='" + email + '\'' + ", mobile='" + mobile + '\'' + ", status=" + status + ", roleIdList=" + roleIdList
                + ", postIdList=" + postIdList + ", orgIdList=" + orgIdList + ", createUserId='" + createUserId + '\'' + ", createTime=" + createTime
                + '}';
    }
}
