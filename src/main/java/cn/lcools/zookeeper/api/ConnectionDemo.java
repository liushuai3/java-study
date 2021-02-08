package cn.lcools.zookeeper.api;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright: Copyright (c) 2021
 *
 * @ClassName: ConnectionDemo
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2021/2/8 10:01
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2021/2/8     liushuai           v1.0.0               修改原因
 */
public class ConnectionDemo {
    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("123.59.196.96:2181", 4000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    //如果收到了服务端的响应事件，连接成功
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        //CONNECTED
        System.out.println(zooKeeper.getState());

        //String rep = zooKeeper.create("/runoob","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //System.out.println(rep);
        byte[] bytes = zooKeeper.getData("/runoob", false, new Stat());
        System.out.println(new String(bytes));
    }
}
