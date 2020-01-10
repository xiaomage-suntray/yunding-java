
package com.devplatform.admin.wechat.menu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

public class WeixinUtil {
    
    public static JSONObject doGetStr(String url) {
        JSONObject jsonObject = null;
        
        return jsonObject;
        
    }
    
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());
            
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            
            httpUrlConn.setRequestMethod(requestMethod);
            
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }
            
            if (outputStr != null) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            String result = buffer.toString();
            System.out.println("http请求返回结果   " + result);
            jsonObject = JSONObject.fromObject(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
