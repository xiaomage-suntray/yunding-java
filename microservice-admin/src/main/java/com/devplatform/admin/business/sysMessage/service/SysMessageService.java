
package com.devplatform.admin.business.sysMessage.service;

import java.io.IOException;
import java.util.Map;

import com.devplatform.admin.business.sysMessage.bean.SysMessage;
import com.devplatform.common.service.MyBaseService;

/**
 * 消息模块的service接口
 * <br>
 * <b>功能：</b>SysMessageService<br>
 * @author 代码生成器产生
 */
public interface SysMessageService extends MyBaseService<SysMessage> {
    
    /**
     * 发送消息
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月12日下午3:45:13
     */
    void sendMessages();
    
    /**
     * 获取微信token
     * Description:
     * Company:
     * @author suochaochao
     * 2019年4月18日下午4:43:27
     * @param appid
     * @param appsecret
     * @return
     */
    Map<String, Object> getAccessToken() throws IOException;
    
}
