
package com.devplatform.admin.wechat.menu;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateMenu {
    
    public static final String APPID = "wx603157d492dc9e5e";
    
    public static final String APPSECRET = "e6ba12b5a0423eb7a8106b73580a4d14";
    
    public static void main(String[] args) throws Exception {
        createCustomMenu();
        
    }
    
    //获取token
    public static String getAccessToken() throws Exception {
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
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
        
        //获取access_token
        JSONObject jsonObject = new JSONObject(message);
        return jsonObject.getString("access_token");
    }

    //写菜单json串
    public static String getMenuStr() throws JSONException {
        JSONObject firstLevelMenu = new JSONObject();//一级菜单
        JSONArray firstLevelMenuArray = new JSONArray();//一级菜单列表

        //一级菜单 . 我的待办
        JSONObject firstLevelMenuContext1 = new JSONObject();
        firstLevelMenuContext1.put("type", "view");
        firstLevelMenuContext1.put("name", "限号查询");
        firstLevelMenuContext1.put("url", "http://49.233.133.233/#/car");

        //一级菜单 . 我的已办
        JSONObject firstLevelMenuContext2 = new JSONObject();
        firstLevelMenuContext2.put("type", "view");
        firstLevelMenuContext2.put("name", "天气查询");
        firstLevelMenuContext2.put("url", "http://49.233.133.233/#/weatherInfo");
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        firstLevelMenuArray.put(firstLevelMenuContext1);
        firstLevelMenuArray.put(firstLevelMenuContext2);
        
        firstLevelMenu.put("button", firstLevelMenuArray);
        
        return firstLevelMenu.toString();
    }
    
    //创建菜单
    public static void createCustomMenu() throws Exception {
        String custmMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
        
        //获取access_token
        String accessToken = getAccessToken();
        custmMenuUrl = custmMenuUrl + accessToken;
        
        URL url = new URL(custmMenuUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(getMenuStr().getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
        
        InputStream inputStream = connection.getInputStream();
        int size = inputStream.available();
        byte[] bs = new byte[size];
        inputStream.read(bs);
        String message = new String(bs, "UTF-8");
        
        System.out.println(message);
    }
    
}
