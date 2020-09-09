package cn.cherry.blog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @description: MP配置
 * @author: Cherry
 * @create: 2020-09-08 13:45
 **/
public class MybatisPlusConfig {

    
    /**
    * @Description: 分页插件
    * @Param: 
    * @return: 
    */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        paginationInterceptor.setLimit(你的最大单页限制数量,默认500条,小于0如-1 不受限制);
        paginationInterceptor.setLimit(5000);
        return paginationInterceptor;
    }

}
