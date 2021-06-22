package com.lee.test.httpclient.module;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cdlipan5
 * @create 2021-05-18 下午10:14
 **/
@Getter
@Setter
public class MethodDataForMlaasReq {
    private String monitorType = "MethodDataForMlaas";
    private String scope = "KEY";
    private String scopeValues = "";
    private String dagaCycle = "day";
    private String hosts = "";
    private String dataType = "TotalCount";
    private String startTime = "20210418000000";
    private String endTime = "20210519000000";

    public MethodDataForMlaasReq(String scopeValues) {
        this.scopeValues = scopeValues;
    }
}
