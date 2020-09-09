package cn.cherry.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @description:
 * @author: Cherry
 * @create: 2020-09-04 09:19
 **/
@SpringBootApplication
@MapperScan("cn.cherry.blog.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
