package cn.cherry.blog.config;

import cn.cherry.blog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sun.java2d.opengl.OGLContext;

import java.security.Key;
import java.util.UUID;

/**
 * @description:
 * @author: Cherry
 * @create: 2020-09-15 15:05
 **/

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void  insertTest(){
        String id = "123123123";
        redisUtil.set(id,id,300);
        boolean result = redisUtil.hasKey(id);
        log.info("key是否存在:{}",result);
        Object o = redisUtil.get(id);
        log.info("key对应的值:{}",id);
    }

}
