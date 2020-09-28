package com.lee.test.algorithm.hash.consistent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

/**
 * @author LEE_PAN
 * @description
 * @date 2020/9/27 23:59
 */
public class ConsistentHash {
    private static final int VIRTUAL_NODE_NUM = 256;

    private static List<String> serverList = Lists.newArrayList("192.168.0.1", "192.168.0.2", "192.168.0.3", "192.168.0.4", "192.168.0.5");
    private static List<String> dataList = Lists.newArrayList("香蕉", "苹果", "枇杷", "草莓", "柠檬", "桃子", "榴莲", "梨", "红枣", "石榴", "葡萄", "橘子", "芒果", "西瓜", "柿子", "山竹", "百香果", "杏子", "火龙果", "桂圆", "荸荠", "柚子", "桑葚", "李子", "菠萝", "菠萝蜜", "槟榔", "橙子", "杨梅", "银杏", "无花果", "乌梅", "甘蔗", "哈密瓜", "人参果", "樱桃", "圣女果", "黄桃", "雪莲果", "蛇皮果", "莲雾", "红毛丹", "鳄梨", "海棠果", "释迦果", "蛋黄果", "香瓜", "青梅 ", "山楂 ", "水蜜桃 ", "荔枝", "金桔 ", "柑桔 ", "椰子", "杨桃", "木瓜", "莲雾");

    private static SortedMap<Integer, String> serverNodeMap = Maps.newTreeMap();
    private static SortedMap<Integer, String> serverNodeWithVirtualNodeMap = Maps.newTreeMap();

    private static Map<String, String> cacheMap = Maps.newHashMapWithExpectedSize(serverList.size());

    static {
        serverList.forEach(server -> {
            addServer(server, false);
            addServer(server, true);
        });
    }

    private static void addServer(String server, boolean withVirtualNode) {
        if (withVirtualNode) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                String virtualNodeName = server + "#" + i;
                int hash = hash(virtualNodeName);
                serverNodeWithVirtualNodeMap.put(hash, server);
            }
        } else {
            int hash = hash(server);
            serverNodeMap.put(hash, server);
        }
    }

    private static void delServerNode(String server, boolean withVirtualNode) {
        Set<Map.Entry<Integer, String>> entries = (withVirtualNode ? serverNodeWithVirtualNodeMap : serverNodeMap).entrySet();
        entries.removeIf(entry -> server.equals(entry.getValue()));
    }

    public static String getServerNode(SortedMap<Integer, String> serverNodeMap, String data) {
        int dataHash = hash(data);
        SortedMap<Integer, String> subMap = serverNodeMap.tailMap(dataHash);
        int serverHash;
        if (subMap.size() == 0) {
            serverHash = serverNodeMap.firstKey();
        } else {
            serverHash = subMap.firstKey();
        }

        return serverNodeMap.get(serverHash);
    }

    private static int hash(String str) {
//        final int p = 16777619;
//        int hash = (int)2166136261L;
//        for (int i = 0; i < str.length(); i++)
//            hash = (hash ^ str.charAt(i)) * p;
//        hash += hash << 13;
//        hash ^= hash >> 7;
//        hash += hash << 3;
//        hash ^= hash >> 17;
//        hash += hash << 5;
//
//        // 如果算出来的值为负数则取其绝对值
//        if (hash < 0)
//            hash = Math.abs(hash);
//        return hash;
        return DigestUtils.md5Hex(str).hashCode();

    }

    private static void loadCache(boolean withVirtualNode) {
        cacheMap.clear();
        dataList.forEach(data -> {
            String serverNode = getServerNode(withVirtualNode ? serverNodeWithVirtualNodeMap : serverNodeMap, data);
            if (cacheMap.containsKey(serverNode)) {
                cacheMap.put(serverNode, cacheMap.get(serverNode) + ", " + data);
            } else {
                cacheMap.put(serverNode, data);
            }
        });
    }

    private static <Key, Value> void print(Map<Key, Value> result) {
        result.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    private static <Key, Value> void print(Map<Key, Value> result, Collection<String> servers) {
        servers.forEach(server -> System.out.println(server + ":" + result.get(server)));
    }

    public static void main(String[] args) {
        boolean withVirtualNode = true;
        print(serverNodeMap);
//        print(serverNodeWithVirtualNodeMap);
        Collection<String> servers = serverNodeMap.values();
        System.out.println("==========================start==========================");
        loadCache(withVirtualNode);
        print(cacheMap, servers);
        System.out.println("==========================delServer==========================");
        delServerNode("192.168.0.5", withVirtualNode);
        loadCache(withVirtualNode);
        print(cacheMap, servers);
        System.out.println("==========================addServer==========================");
        addServer("192.168.0.6", withVirtualNode);
        loadCache(withVirtualNode);
        print(cacheMap, servers);

        System.out.println("==========================end==========================");
    }

}
