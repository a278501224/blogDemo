package cn.cherry.blog.config;

import cn.cherry.blog.util.SecurityUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.security.krb5.Config;

import javax.sql.DataSource;

/**
 * @description: 数据库连接配置
 * @author: Cherry
 * @create: 2020-09-07 10:10
 **/

@Slf4j
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
//      log.info(SecurityUtil.encrypt("yourpassword","0d7b43aca1c3f107"));
        String decryptPassword = SecurityUtil.decrypt(password, "0d7b43aca1c3f107");
        config.setPassword(decryptPassword);
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }



}
