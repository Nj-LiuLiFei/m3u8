package com;

import com.http.HttpClient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParseM implements BaseHttpCallBack {

    private String httpUrl="";
    private List<String> tsUrlList = new ArrayList<String>();
    public ParseM(String httpUrl){
        this.httpUrl = httpUrl;
    }
    public void requestM(){
        System.out.println("正在发送请求："+httpUrl);
        HttpClient.doGet(httpUrl,this);
        new DownloadTSFile(tsUrlList).Download();
    }

    public void httpCallBack(int responseCode,InputStream inputStream) {
        System.out.println("正在解析TS路径.....");
        if(responseCode == 200){
            try {
                // 封装输入流is，并指定字符集
                BufferedReader br  = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String lineStr = null;
                while ((lineStr = br.readLine()) != null) {
                    if((lineStr.indexOf("http://")>-1||lineStr.indexOf("https://")>-1)&&lineStr.lastIndexOf(".ts")>-1){
                        tsUrlList.add(lineStr);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String item:tsUrlList) {
                System.out.println(item);
            }
        }
    }

    public void error(InputStream inputStream) {

    }

    public void httpException() {

    }
}
