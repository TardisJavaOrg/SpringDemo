package com.huixiong.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;


@Configuration
@ComponentScan
@PropertySource("classpath:jdbc.properties")
public class JdbcConfig {

    // 从配置文件中注入变量
    @Value("${jdbc.url}")
    String jdbcUrl;

    @Value("${jdbc.username}")
    String jdbcUsername;

    @Value("${jdbc.password}")
    String jdbcPassword;

    /**
     * 自动注入 数据库连接池，用于创建连接
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
     * 自动注入 jdbcTemplate
     * @param dataSource
     * @return
     */
    @Bean
    JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        StudentService bean = context.getBean(StudentService.class);
        Student student = bean.getStudentbyId(1);
        System.out.println(student.getName());
    }
}

/**
 * String jdbc_driver = "com.mysql.cj.jdbc.Driver";
 * String db_url = "jdbc:mysql://localhost:3306/spring_demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
 * String user = "root";
 * String pwd = "123456";
 * try {
 * Class.forName(jdbc_driver);
 * Connection conn = null;
 * Statement statement = null;
 * conn = DriverManager.getConnection(db_url,user,pwd);
 * <p>
 * statement = conn.createStatement();
 * String sql = "select * from students";
 * ResultSet resultSet = statement.executeQuery(sql);
 * while (resultSet.next()){
 * System.out.println(resultSet.getString("name"));
 * System.out.println(resultSet.getString("age"));
 * }
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 */

