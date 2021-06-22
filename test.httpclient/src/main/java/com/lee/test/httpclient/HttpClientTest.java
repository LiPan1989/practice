package com.lee.test.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.lee.test.httpclient.module.AppKeyListReq;
import com.lee.test.httpclient.module.DeleteReq;
import com.lee.test.httpclient.module.MethodDataForMlaasReq;
import com.lee.test.httpclient.module.ResponseVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author cdlipan5
 * @create 2021-05-18 下午9:39
 **/
public class HttpClientTest {
    private static final String QueryURL = "http://open.ump.jd.com/queryMonitorData";
    private static final String OperateURL = "http://open.ump.jd.com/operateMonitorData";
    private static final String Token = "a85a5b9fe3ca489a86bcedfaa93c4cd1";
    public static final String AppName = "b2bwaredal";

    public static void main(String[] args) {
        List<String> umpKeys = getMethodKeys();
        if (umpKeys != null) {
            int total = umpKeys.size();
            int increment = 0;
            List<List<String>> partitions = Lists.partition(umpKeys, 20);
            for (List<String> partition : partitions) {
                increment += 20;
                System.out.println("total: " + total + ", residue: " + (total - increment));
                List<String> needDelKeys = needDelKeys(partition);
                needDelKeys.forEach(HttpClientTest::doDel);
            }
        }

    }

    private static List<String> getMethodKeys() {
        AppKeyListReq appKeyListReq = new AppKeyListReq();
        appKeyListReq.setPlatform("jdos");
        appKeyListReq.setMonitorType("AppKeyList");
        appKeyListReq.setAppName(AppName);
        appKeyListReq.setQueryType("method");
        String requestBody = JSON.toJSONString(appKeyListReq);
        String result = doPost(QueryURL, requestBody, Token);
        ResponseVo responseVo = JSON.parseObject(result, ResponseVo.class);
        List<Map<String, String>> requestResults = responseVo.getResult();
        if (!CollectionUtils.isEmpty(requestResults)) {
            return requestResults.stream().map(requestResult -> requestResult.get("accessKey")).collect(Collectors.toList());
        }
        return null;
    }

    private static List<String> needDelKeys(List<String> keys) {

        List<String> needDelKeys = Lists.newArrayList();
        MethodDataForMlaasReq methodDataForMlaasReq = new MethodDataForMlaasReq(Joiner.on(",").join(keys));
        String requestBody = JSON.toJSONString(methodDataForMlaasReq);
        String responseResult = doPost(QueryURL, requestBody, Token);
        JSONObject jsonObject = JSON.parseObject(responseResult);
        Map<String, String> responseInfo = jsonObject.getObject("responseInfo", Map.class);
        if ("fail".equals(responseInfo.get("state"))) {
            System.out.println(responseResult);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return needDelKeys(keys);
        }

        Map<String, JSONArray> result = (Map<String, JSONArray>) jsonObject.get("result");
        result.forEach((key, value) -> {
            if (value.isEmpty() || key.contains("yufa.com.jd.b2b.ware.pool.service.sync.pull.PullHelper")) {
                System.out.println("Del key -->" + key);
                needDelKeys.add(key);
            }
        });
        return needDelKeys;
    }

    private static void doDel(String key) {
        DeleteReq deleteReq = new DeleteReq(key);
        String requestBody = JSON.toJSONString(deleteReq);
        String responseResult = doPost(OperateURL, requestBody, Token);
//        System.out.println(responseResult);

    }

    private static String doPost(String restfulURL, String params, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token", token);
        HttpEntity<String> entity = new HttpEntity<String>(params, headers);
        final ResponseEntity<String> result = restTemplate.postForEntity(restfulURL, entity, String.class);
//        System.out.println("返回结果：" + result.getBody());
//        System.out.println("返回状态码：" + result.getStatusCode());
        return result.getBody();
    }
}
