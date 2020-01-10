
package com.devplatform.admin.business.sysMessage.model;

import com.devplatform.admin.common.model.BaseModel;

/**
 * 消息模块的Model
 * <br>
 * @author 代码生成器产生
 */
public class SysMessageModel extends BaseModel {
    
    private java.lang.String id;//
    
    private java.lang.String msgType;//消息类型（例如，发票审核，合同审核）
    
    private java.lang.String msgTitle;//消息标题
    
    private java.lang.String msgSkipurl;//跳转路径
    
    private java.lang.String msgParameter;//附带参数
    
    private java.lang.String msgInitiatorUserId;//消息发起人
    
    private java.lang.String msgRecipientUserId;//消息接收人
    
    private java.lang.String msgDisposeUserId;//消息处理人
    
    private Integer msgState;//1未处理，2已处理
    
    private java.util.Date msgCreateTime;//消息发起时间
    
    private java.util.Date msgCreateDisposeTime;//消息处理时间
    
    private Integer msgPushStatus;//1.未发送，2.已发送
    
    /**
     * 的getter方法
     */
    public java.lang.String getId() {
        return id;
    }
    
    /**
     * 的setter方法
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }
    
    /**
     * 消息类型（例如，发票审核，合同审核）的getter方法
     */
    public java.lang.String getMsgType() {
        return msgType;
    }
    
    /**
     * 消息类型（例如，发票审核，合同审核）的setter方法
     */
    public void setMsgType(java.lang.String msgType) {
        this.msgType = msgType;
    }
    
    /**
     * 消息标题的getter方法
     */
    public java.lang.String getMsgTitle() {
        return msgTitle;
    }
    
    /**
     * 消息标题的setter方法
     */
    public void setMsgTitle(java.lang.String msgTitle) {
        this.msgTitle = msgTitle;
    }
    
    /**
     * 跳转路径的getter方法
     */
    public java.lang.String getMsgSkipurl() {
        return msgSkipurl;
    }
    
    /**
     * 跳转路径的setter方法
     */
    public void setMsgSkipurl(java.lang.String msgSkipurl) {
        this.msgSkipurl = msgSkipurl;
    }
    
    /**
     * 附带参数的getter方法
     */
    public java.lang.String getMsgParameter() {
        return msgParameter;
    }
    
    /**
     * 附带参数的setter方法
     */
    public void setMsgParameter(java.lang.String msgParameter) {
        this.msgParameter = msgParameter;
    }
    
    /**
     * 消息发起人的getter方法
     */
    public java.lang.String getMsgInitiatorUserId() {
        return msgInitiatorUserId;
    }
    
    /**
     * 消息发起人的setter方法
     */
    public void setMsgInitiatorUserId(java.lang.String msgInitiatorUserId) {
        this.msgInitiatorUserId = msgInitiatorUserId;
    }
    
    /**
     * 消息接收人的getter方法
     */
    public java.lang.String getMsgRecipientUserId() {
        return msgRecipientUserId;
    }
    
    /**
     * 消息接收人的setter方法
     */
    public void setMsgRecipientUserId(java.lang.String msgRecipientUserId) {
        this.msgRecipientUserId = msgRecipientUserId;
    }
    
    /**
     * 消息处理人的getter方法
     */
    public java.lang.String getMsgDisposeUserId() {
        return msgDisposeUserId;
    }
    
    /**
     * 消息处理人的setter方法
     */
    public void setMsgDisposeUserId(java.lang.String msgDisposeUserId) {
        this.msgDisposeUserId = msgDisposeUserId;
    }
    
    /**
     * 1未处理，2已处理的getter方法
     */
    public Integer getMsgState() {
        return msgState;
    }
    
    /**
     * 1未处理，2已处理的setter方法
     */
    public void setMsgState(Integer msgState) {
        this.msgState = msgState;
    }
    
    /**
     * 消息发起时间的getter方法
     */
    public java.util.Date getMsgCreateTime() {
        return msgCreateTime;
    }
    
    /**
     * 消息发起时间的setter方法
     */
    public void setMsgCreateTime(java.util.Date msgCreateTime) {
        this.msgCreateTime = msgCreateTime;
    }
    
    /**
     * 消息处理时间的getter方法
     */
    public java.util.Date getMsgCreateDisposeTime() {
        return msgCreateDisposeTime;
    }
    
    /**
     * 消息处理时间的setter方法
     */
    public void setMsgCreateDisposeTime(java.util.Date msgCreateDisposeTime) {
        this.msgCreateDisposeTime = msgCreateDisposeTime;
    }
    
    public Integer getMsgPushStatus() {
        return msgPushStatus;
    }
    
    public void setMsgPushStatus(Integer msgPushStatus) {
        this.msgPushStatus = msgPushStatus;
    }
    
}
