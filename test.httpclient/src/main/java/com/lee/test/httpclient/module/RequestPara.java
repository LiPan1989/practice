package com.lee.test.httpclient.module;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cdlipan5
 * @create 2021-05-18 下午9:45
 **/
@Getter
@Setter
public class RequestPara {
    private String monitorType;
    private String appName;
    private String platform;
    private String queryType;
}
