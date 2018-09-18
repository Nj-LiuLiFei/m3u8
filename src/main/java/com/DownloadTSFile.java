package com;

import com.http.HttpClient;

import java.io.*;
import java.util.List;
import java.util.UUID;

import static java.lang.System.out;

public class DownloadTSFile implements BaseHttpCallBack{
    private List<String> tsUrlList;
    private FileOutputStream fileOutputStream=null;
    public DownloadTSFile(List<String> tsUrlList){
        this.tsUrlList=tsUrlList;
    }
    public void Download(){
        if(tsUrlList.size()>0){
            try {
                fileOutputStream = new FileOutputStream(new File("E:\\"+UUID.randomUUID()+".mp4"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (String url:tsUrlList) {
                HttpClient.doGet(url,this);
            }
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void httpCallBack(int responseCode, InputStream inputStream) {
        if(responseCode == 200){
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            try {
                while ((byteread = inputStream.read(tempbytes)) != -1) {
                    fileOutputStream.write(tempbytes, 0, byteread);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void error(InputStream inputStream) {

    }

    public void httpException() {

    }
}
