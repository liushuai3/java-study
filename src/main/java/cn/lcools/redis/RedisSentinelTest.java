package cn.lcools.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright: Copyright (c) 2020
 *
 * @ClassName: RedisSentinelTest
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2020/9/25 16:05
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2020/9/25     liushuai           v1.0.0               修改原因
 */
public class RedisSentinelTest {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        Set<String> sentinels = new HashSet<String>();
        String hostAndPort1 = "118.24.120.220:26379";
        String hostAndPort2 = "123.59.196.96:26379";
        String hostAndPort3 = "47.111.95.247:26379";
        sentinels.add(hostAndPort1);
        sentinels.add(hostAndPort2);
        sentinels.add(hostAndPort3);
        String clusterName = "mymaster";
        String password = "ls@123456";
        JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels, password);
        Jedis jedis = null;
        try {
            jedis = redisSentinelJedisPool.getResource();
            jedis.set("key6", "value6");
            System.out.println(jedis.get("key6"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisSentinelJedisPool.destroy();
        }
        redisSentinelJedisPool.close();
    }

}
