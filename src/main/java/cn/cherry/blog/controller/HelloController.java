package cn.cherry.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 调试项目使用
 * @author: Cherry
 * @create: 2020-09-04 09:33
 **/
@Api(tags = "调试")
@CrossOrigin(maxAge = 3600)
@Slf4j
@Controller
@RequestMapping("/demo")
public class HelloController {


    @ApiOperation("调试项目启动是否正常")
    @RequestMapping("/hello")
    public String hello(){
        log.info("--------helloworld-------");
        return "demo";
    }
}
