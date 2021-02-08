package cn.lcools.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * Copyright: Copyright (c) 2021
 *
 * @ClassName: CuratorDemo
 * @Description: Curator 是 Netflix 公司开源的一套 zookeeper 客户端框架，解决了很多 Zookeeper 客户端非常底层的细节开发工作，包括连接重连、反复注册 Watcher 和 NodeExistsException 异常等。
 * <p>
 * Curator 包含了几个包：
 * <p>
 * curator-framework：对 zookeeper 的底层 api 的一些封装。
 * curator-client：提供一些客户端的操作，例如重试策略等。
 * curator-recipes：封装了一些高级特性，如：Cache 事件监听、选举、分布式锁、分布式计数器、分布式 Barrier 等。
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2021/2/8 10:16
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2021/2/8     liushuai           v1.0.0               修改原因
 */
public class CuratorDemo {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                builder().connectString("123.59.196.96:2181").
                sessionTimeoutMs(4000).retryPolicy(new
                ExponentialBackoffRetry(1000, 3)).
                namespace("").build();
        curatorFramework.start();
        Stat stat = new Stat();
        //查询节点数据
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/runoob");
        System.out.println(new String(bytes));
        curatorFramework.close();
    }
}
