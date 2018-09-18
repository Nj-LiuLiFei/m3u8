package com;

import java.io.InputStream;

public class Demo{

    public static void main(String args[]){
        String httpUrl = "http://cache.utovr.com/201508270528174780.m3u8";
        new ParseM(httpUrl).requestM();
        System.out.println("执行完成");
    }
}
