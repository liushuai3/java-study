package cn.lcools.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * Copyright: Copyright (c) 2020
 *
 * @ClassName: Test
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2020/9/30 10:56
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2020/9/30     liushuai           v1.0.0               修改原因
 */
@Component
public class SentinelTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void testString() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("name", "丁洁");
        System.out.println(operations.get("name"));
    }
}
