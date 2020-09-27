package com.lee.test.zk.salon;

import org.apache.helix.manager.zk.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author LEE_PAN
 * @description
 * @date 2020/5/24 23:35
 */
public class Server extends ZkConnectionHolder implements Runnable {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    /**
     * 向ZooKeeper注册当前服务端
     */
    public void register() throws Exception {
        String path = SERVER_PATH  + "/" + port;
        if (client.checkExists().forPath(path)!=null) {
            client.delete().forPath(path);
        }

        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, ("127.0.0.1:" + port).getBytes());
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            register();
            Socket socket;
            //保持服务器端程序一直运行
            while (true) {
                System.out.println("============" + serverSocket + "等待连接============");
                socket = serverSocket.accept();
                //单开一个线程处理每一个客户端连接
                Thread thread = new Thread(new ServerHandler(socket));
                thread.start();
                System.out.println("============" + thread + "启动处理请求============");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Server(8081), "Server1").start();
        new Thread(new Server(8082), "Server2").start();
        new Thread(new Server(8083), "Server3").start();
        new Thread(new Server(8084), "Server4").start();
        new Thread(new Server(8085), "Server5").start();

    }
}