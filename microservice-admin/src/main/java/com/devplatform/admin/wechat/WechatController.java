
package com.devplatform.admin.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devplatform.admin.business.sysMessage.service.SysMessageService;
import com.devplatform.admin.common.utils.AbstractController;
import com.devplatform.admin.common.utils.JSONUtil;
import com.devplatform.admin.modules.sys.bean.SysUserEntity;
import com.devplatform.admin.modules.sys.service.SysUserService;
import com.devplatform.common.util.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "wechat认证接口" }) //swagger注解
@RestController
@RequestMapping("/wechat")
public class WechatController extends AbstractController {
    
    private final static Logger log = LoggerFactory.getLogger(WechatController.class);
    
    @Value("${wechat.appid}")
    private String appid;
    
    @Value("${wechat.appsecret}")
    private String appsecret;
    
    @Value("${wechat.template_id}")
    private String template_id;
    
    @Autowired
    private SysMessageService sysMessageService;
    
    @Autowired
    private SysUserService sysUserService;
    
    /**
     * 微信测试认证
     * Description:
     * Company:
     * @author suochaochao
     * 2019年4月18日上午10:30:31
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @ApiOperation(value = "微信测试认证", notes = "微信测试认证")
    @GetMapping("/verification")
    public void verification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println("signature:" + signature);
        System.out.println("timestamp:" + timestamp);
        System.out.println("nonce:" + nonce);
        System.out.println("echostr:" + echostr);
        PrintWriter pw = response.getWriter();
        pw.append(echostr);
        pw.flush();
    }
    
    /**
     * 获取appid
     * Description:
     * Company:
     * @author suochaochao
     * 2019年4月18日上午10:30:31
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @ApiOperation(value = "获取appid", notes = "获取appid")
    @GetMapping("/getAppid")
    public R getAppid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("appid", appid);
        resultMap.put("appsecret", appsecret);
        return R.ok().put("data", resultMap);
    }
    
    /**
     * 授权获取openId
     * Description:
     * Company:
     * @author suochaochao
     * 2019年4月18日下午3:47:14
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws JSONException 
     */
    @ApiOperation(value = " 授权获取openId", notes = " 授权获取openId")
    @GetMapping("/saveOpenid")
    public R saveOpenid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String userId = request.getParameter("userId");
        log.info("授权返回数据code:" + code);
        log.info("授权返回数据userId:" + userId);
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code
                + "&grant_type=authorization_code";
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
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = JSONUtil.jsonToMap(message, Map.class);
        SysUserEntity entity = new SysUserEntity();
        entity.setOpenid(resultMap.get("openid") + "");
        entity.setUserId(userId);
        sysUserService.updateById(entity);
        
        //获取access_token
        log.info("授权返回数据string:" + message);
        return null;
    }
    
    /**
     * 获取accessToken
     * Description:
     * Company:
     * @author suochaochao
     * 2019年4月18日下午3:47:14
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws JSONException 
     */
    @ApiOperation(value = "获取accessToken", notes = "获取accessToken")
    @GetMapping("/getAccessToken")
    public R getAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取access_token
        return R.ok().put("data", sysMessageService.getAccessToken());
    }
    
    /**
    * 微信推送消息
    * Description:
    * Company:
    * @author suochaochao
    * 2019年4月19日下午3:08:05
    * @param request
    * @param response
    * @return
    * @throws IOException
    */
    @ApiOperation(value = "微信推送消息", notes = "微信推送消息")
    @GetMapping("/sendWechat")
    public R sendWechat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取access_token
        String oppenid = "oBn0359pBA4g58Ohf7UwEGYJjC-k";
        String access_token = "20_XpIUYBLU7TNQS54PGshGh8Z6CKVEx1WSnY7yWMqv2OxwC1IK2wRbIyt_9-GSJ_1Nc47wn_p4GOT3f4cmqI1549r7xvfdAgfsFDdlq7rYk95pphpGLjSfn3L0fNdBfWa-SGI_09Mj6BKEkB4tTCUbAHAYHZ";
        String acessTokenUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
        URL url = new URL(acessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Map<String, Object> postMap = new HashMap<String, Object>();
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        postMap.put("touser", oppenid);
        postMap.put("template_id", "nDOeqmaGAwXBlrWBjw8iMCTWGA20H3dqGatD3z2KY94");
        //        postMap.put("url", "http://new013.suntrayoa.com/#/mobile/tasksEnd");
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("value", "您有新的消息");
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
        return R.ok().put("data", message);
    }
}
