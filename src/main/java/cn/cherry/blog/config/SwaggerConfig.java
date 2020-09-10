package cn.cherry.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: Swagger配置
 * @author: Cherry
 * @create: 2020-09-04 09:38
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
                .title("Swagger接口测试文档")
                .description("后台接口说明")
                .contact(new Contact("cherry", null, "yichaotang@qq.com"))
                .version("版本号:1.0")
                .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.cherry.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
