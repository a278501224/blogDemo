package cn.cherry.blog.interceptor;

import cn.cherry.blog.common.ResponseEnum;
import cn.cherry.blog.common.ResponseResult;
import cn.cherry.blog.common.ResponseUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 自定义拦截器
 * @author: Cherry
 * @create: 2020-09-04 17:30
 **/
@Slf4j
public class MyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        log.info("Interceptor preHandle method is running !");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        log.info("Interceptor postHandler method is running !");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        log.info("Interceptor afterCompletion method is running !");
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("Interceptor afterConcurrentHandlingStarted method is running !");
    }


    /**
    * @Description: 处理用户名过期、TOKEN过期
    * @Param: 
    * @return: 
    */
    private void handleUserNameOrToken(HttpServletResponse response,String message){
        log.info("handleUserNameOrToken,message:{}",message);
        //返回json数据
        ResponseResult result = ResponseUtil.fail(ResponseEnum.UNAUTHORIZED.getResCode(), message);
        //处理编码方式,防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        response.setStatus(Integer.parseInt(ResponseEnum.UNAUTHORIZED.getResCode()));
        //塞进HttpServletResponse中返回给前台

        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
           log.error("handleUserNameOrToken IOException");
        }


    }


}
