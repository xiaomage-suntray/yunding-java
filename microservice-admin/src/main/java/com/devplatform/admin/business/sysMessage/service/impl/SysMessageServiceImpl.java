
package com.devplatform.admin.business.sysMessage.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.devplatform.admin.business.sysMessage.bean.SysMessage;
import com.devplatform.admin.business.sysMessage.dao.SysMessageDao;
import com.devplatform.admin.business.sysMessage.model.SysMessageModel;
import com.devplatform.admin.business.sysMessage.service.SysMessageService;
import com.devplatform.admin.business.wechat.bean.WeixinAccessToken;
import com.devplatform.admin.business.wechat.dao.WeixinAccessTokenDao;
import com.devplatform.admin.business.wechat.service.WeixinAccessTokenService;
import com.devplatform.admin.common.utils.JSONUtil;
import com.devplatform.admin.webSocket.WebSocketServer;
import com.devplatform.common.service.impl.MyBaseServiceImpl;

/**
 * 消息模块的service接口实现类
 * <br>
 * <b>功能：</b>SysMessageServiceImpl<br>
 * @author 代码生成器产生
 */
@Service("sysMessageService")
public class SysMessageServiceImpl extends MyBaseServiceImpl<SysMessageDao, SysMessage> implements SysMessageService {
    
    private final static Logger log = LoggerFactory.getLogger(SysMessageServiceImpl.class);
    
    @Value("${wechat.appid}")
    private String appid;
    
    @Value("${wechat.appsecret}")
    private String appsecret;
    
    @Autowired
    WeixinAccessTokenService weixinAccessTokenService;
    
    @Autowired
    WeixinAccessTokenDao weixinAccessTokenDao;
    
    @Autowired
    SysMessageDao sysMessageDao;
    
    /**
     * 定时发送消息
     * Description:
     * Company:
     * @author suochaochao
     * 2019年3月12日下午3:45:33
     */
    @Override
    public void sendMessages() {
        
        //获取所有未发送过的数据
        SysMessageModel model = new SysMessageModel();
        model.setMsgPushStatus(1);
        model.setMsgState(1);
        model.setRows(10);
        List<SysMessage> listsm = sysMessageDao.queryByListByUser(model);
        
        if (null != listsm && listsm.size() > 0) {
            //获取最新access_token
            WeixinAccessToken weixinAccessToken = weixinAccessTokenDao.queryByNewAccessToken();
            WebSocketServer wss = new WebSocketServer();
            for (SysMessage bean : listsm) {
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("fromUserId", "sys");
                    map.put("toUserId", bean.getMsgRecipientUserId());
                    map.put("content", bean.getMsgTitle());
                    JSONObject json = (JSONObject) JSONObject.toJSON(map);
                    wss.onMessage(json.toString());
                    
                    //微信端发消息
                    if (null != bean && null != bean.getOpenid() && !bean.getOpenid().equals("")) {
                        if (null != weixinAccessToken && null != weixinAccessToken.getAccessToken()) {
                            sendWechat(bean.getOpenid(), weixinAccessToken.getAccessToken(), bean.getMsgTitle());
                        }
                    }
                }
                catch (Exception e) {
                    // TODO: handle exception
                    log.info("系统发送消息发生错误：" + e.getMessage());
                }
                finally {
                    bean.setMsgPushStatus(2);
                    sysMessageDao.updateById(bean);
                }
                
            }
            
        }
    }
    
    @Override
    public Map<String, Object> getAccessToken() throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        log.info("授权获取openId");
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
        URL url = new URL(accessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        
        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size = inputStream.available();
        byte[] bs = new byte[size];
        inputStream.read(bs);
        String message = new String(bs, "UTF-8");
        //保存
        WeixinAccessToken wat = new WeixinAccessToken();
        resultMap = JSONUtil.jsonToMap(message, Map.class);
        wat.setCreatedTime(new Date());
        wat.setAccessToken(resultMap.get("access_token") + "");
        weixinAccessTokenService.save(wat);
        //获取access_token
        return resultMap;
    }
    
    public String sendWechat(String oppenid, String access_token, String msg) throws IOException {
        //获取access_token
        String acessTokenUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        URL url = new URL(acessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Map<String, Object> postMap = new HashMap<String, Object>();
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        postMap.put("touser", oppenid);
        postMap.put("template_id", "nDOeqmaGAwXBlrWBjw8iMCTWGA20H3dqGatD3z2KY94");
        //        postMap.put("url", "http://new013.suntrayoa.com/#/mobile/tasksEnd");
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("value", msg);
        valueMap.put("color", "#173177");
        dataMap.put("message", valueMap);
        postMap.put("data", dataMap);
        String json = JSONUtil.mapToJson(postMap, true);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(json.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
        
        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size = inputStream.available();
        byte[] bs = new byte[size];
        inputStream.read(bs);
        String message = new String(bs, "UTF-8");
        return message;
    }
    
}
