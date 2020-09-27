package com.lee.test.algorithm.hash.consistent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author LEE_PAN
 * @description
 * @date 2020/9/27 23:59
 */
public class ConsistentHash {
    private static final int VIRTUAL_NODE_NUM = 5;

    private static List<String> serverList = Lists.newArrayList("192.168.0.1", "192.168.0.2", "192.168.0.3","192.168.0.4");
    private static List<String> dataList = Lists.newArrayList("商品1", "商品2", "商品3", "商品4", "商品5", "商品6", "商品7", "商品8", "商品9", "商品10", "商品11");

    public static SortedMap<Integer, String> getServerNodeWithoutVirtualNode(List<String> serverList) {
        SortedMap<Integer, String> serverNodeMap = Maps.newTreeMap();
        serverList.forEach(server -> {
            serverNodeMap.put(hash(server), server);
        });
        return serverNodeMap;
    }

    public SortedMap<Integer, String> getServerNodeWithVirtualNode(List<String> serverList) {
        SortedMap<Integer, String> serverNodeMap = Maps.newTreeMap();
        serverList.forEach(server -> {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                String virtualNodeName = server + "#" + i;
                serverNodeMap.put(hash(virtualNodeName), server);
            }
        });
        return serverNodeMap;
    }

    public static String getServerName(SortedMap<Integer, String> serverNodeMap, String data) {
        int dataHash = hash(data);
        SortedMap<Integer, String> subMap = serverNodeMap.tailMap(dataHash);
        int serverHash = 0;
        if (subMap.size() == 0) {
            serverHash = serverNodeMap.firstKey();
        } else {
            serverHash = subMap.firstKey();
        }

        String serverName = serverNodeMap.get(serverHash);
        return serverName;
    }

    private static int hash(String str) {
        return DigestUtils.md5Hex(str).hashCode();

    }

    private static void printData2Server(SortedMap<Integer, String> serverNodeMap, List<String> dataList) {
        Map<String, String> result = Maps.newHashMapWithExpectedSize(dataList.size());
        dataList.forEach(data -> {
            String serverName = getServerName(serverNodeMap, data);
            if (result.containsKey(serverName)) {
                result.put(serverName, result.get(serverName) + ", " + data);
            } else {
                result.put(serverName, data);
            }
        });

        result.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }

    @Test
    public void getWithoutVirtualNode() {
        SortedMap<Integer, String> serverNodeMap = getServerNodeWithoutVirtualNode(serverList);
        printData2Server(serverNodeMap, dataList);
    }
}
