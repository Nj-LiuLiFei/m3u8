package com.http;


import com.BaseHttpCallBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {
    // 设置连接主机服务器的超时时间：15000毫秒
    private static  int connectTimeout=15000;
    // 设置读取远程返回的数据时间：60000毫秒
    private static  int readTimeout=60000;

    public static void doGet(String httpUrl, BaseHttpCallBack baseHttpCallBack){
        HttpURLConnection connection = null;
        InputStream is = null;
        int responseCode=0;

        try {
            // 创建远程url连接对象
            URL url =  new URL(httpUrl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            // 发送请求
            connection.connect();
            if(connection.getResponseCode() == 200){
                baseHttpCallBack.httpCallBack(connection.getResponseCode(),connection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            baseHttpCallBack.httpCallBack(-1,null);
        } catch (IOException e) {
            e.printStackTrace();
            baseHttpCallBack.httpCallBack(-2,null);
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 关闭远程连接
        }
    }
}
