package com;

import java.io.InputStream;

public interface BaseHttpCallBack {
    void httpCallBack(int responseCode,InputStream inputStream);
    void error(InputStream inputStream);
    void httpException();
}
