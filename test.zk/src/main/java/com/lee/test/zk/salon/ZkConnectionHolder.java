package com.lee.test.zk.salon;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author cdlipan5
 * @create 2020-06-02 下午5:31
 **/
public abstract class ZkConnectionHolder {

    protected static final String SERVER_PATH = "/Lee/salon/server";

    protected static final CuratorFramework client;

    static {
        client = CuratorFrameworkFactory.builder()
                .connectString("192.168.147.32:2181,192.168.147.33:2181,192.168.147.34:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        client.start();
    }
}
