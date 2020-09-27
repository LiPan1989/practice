package com.lee.test.zk.salon;

/**
 * @author LEE_PAN
 * @description
 * @date 2020/5/24 23:37
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.helix.manager.zk.ZkClient;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 客户端均衡地调用服务端，基于zookeeper实现
 */
public class Client extends ZkConnectionHolder {
    /**
     * 客户端保存所有可用服务端信息
     */
    private static Map<String, String> serverMap = Maps.newHashMap();
    /**
     * 请求计数器
     */
    private static int requestCount = 0;


    /**
     * 客户端扫描所有的server
     */
    private static void initServer() throws Exception {

        List<String> children = client.getChildren().forPath(SERVER_PATH);
        getData(children);
        System.out.println("####从zookeeper注册中心获取的服务器server信息==>serverList:" + serverMap.toString());
        // 监听事件
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, SERVER_PATH, false);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener((curatorFramework, pathChildrenCacheEvent) -> {
            ChildData childData = pathChildrenCacheEvent.getData();


            switch (pathChildrenCacheEvent.getType()){
                case CHILD_ADDED:
                    System.out.println("正在新增子节点：" + childData.getPath());
                    //获取子节点
                    String path =  childData.getPath();
                    serverMap.put(path, new String(client.getData().forPath(path)));
                    System.out.println("serverList:" + serverMap.toString());
                    break;
                case CHILD_REMOVED:
                    System.out.println("子节点被删除");
                    serverMap.remove(childData.getPath());
                    System.out.println("serverList:" + serverMap.toString());
                    break;
            }
        });
    }

    private static void getData(List<String> children) throws Exception {
        for (String serverIp : children) {
            System.out.println("=============="+serverIp+"==============");
            String path = SERVER_PATH + "/" + serverIp;
            serverMap.put(path, new String(client.getData().forPath(path)));
        }
    }

    /**
     * 简单的负载均衡算法
     */
    private static String getServer() {
        int serverCount = serverMap.size();
        String serverHost = Lists.newArrayList(serverMap.values()).get(requestCount % serverCount);
        requestCount++;
        return serverHost;
    }

    private void send(String name) {
        String server = Client.getServer();
        System.out.println("requestCount:" + requestCount);
        System.out.println("获取到的server:" + server);
        String[] config = server.split(":");
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(config[0], Integer.parseInt(config[1]));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(name);
            String resp = in.readLine();
            System.out.println("client receive : " + resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        initServer();
        Client client = new Client();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name;
            try {
                System.out.println("=========请输入=========");
                name = console.readLine();
                if ("exit".equals(name)) {
                    System.exit(0);
                }
                client.send(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}