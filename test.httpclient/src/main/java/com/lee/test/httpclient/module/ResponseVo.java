package com.lee.test.httpclient.module;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author cdlipan5
 * @create 2021-05-18 下午9:45
 **/
@Getter
@Setter
public class ResponseVo {
    private RequestPara requestPara;
    private ResponseInfo responseInfo;
    private List<Map<String, String>> result;
}
