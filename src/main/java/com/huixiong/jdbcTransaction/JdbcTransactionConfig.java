package com.huixiong.jdbcTransaction;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("jdbc.properties")
@EnableTransactionManagement  // 启动声明式事务
public class JdbcTransactionConfig {

    // 从配置文件中注入变量
    @Value("${jdbc.url}")
    String jdbcUrl;

    @Value("${jdbc.username}")
    String jdbcUsername;

    @Value("${jdbc.password}")
    String jdbcPassword;

    /**
     * 自动注入 jdbcTemplate
     *
     * @param dataSource
     * @return
     */
    @Bean
    JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 自动注入 数据库连接池，用于创建连接
     *
     * @return
     */
    @Bean
    DataSource createDataSource() {
        HikariConfig config = new HikariConfig(); // 这是一个连接池工具类
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(jdbcUsername);
        config.setPassword(jdbcPassword);
        config.addDataSourceProperty("autocommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }

    /**
     * 创建事务管理器
     *
     * @param dataSource 连接池，用于创建连接
     * @return
     */
    @Bean
    PlatformTransactionManager createTxManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcTransactionConfig.class);
        StudentService bean = context.getBean(StudentService.class);
        bean.updateStudentById(new Student(1, "huixiong2", -1));
        Student stu = bean.getStudentbyId(1);
        System.out.println(stu.getName());

    }
}
