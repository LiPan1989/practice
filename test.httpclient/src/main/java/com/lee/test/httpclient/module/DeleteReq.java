package com.lee.test.httpclient.module;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cdlipan5
 * @create 2021-05-18 下午10:19
 **/
@Getter
@Setter
public class DeleteReq {
    private String operateType = "method";
    private String type = "delete";
    private String key;
    private String appName = "b2b-ware-biz-svr";

    public DeleteReq(String key) {
        this.key = key;
    }
}
