package com.lee.test.httpclient.module;

import com.lee.test.httpclient.HttpClientTest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cdlipan5
 * @create 2021-05-18 下午10:13
 **/
@Getter
@Setter
public class AppKeyListReq {
    private String monitorType;
    private String appName = HttpClientTest.AppName;
    private String platform;
    private String queryType;
}
