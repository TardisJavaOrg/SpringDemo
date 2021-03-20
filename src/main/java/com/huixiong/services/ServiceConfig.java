package com.huixiong.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Configuration: 注解，标识Spring的入口
 * ComponentScan： 注解，自动扫描当前类的包及其子包，把所有包含注解 @Component 的Ben自动创建出来。根据Autowired装配到内存。
 */
@Configuration
@ComponentScan
public class ServiceConfig {
    public static void main(String[] args) {
        /**
         * 使用配置文件注入
         * ClassPathXmlApplicationContext 用于加载XML配置文件，饼返回一个ApplicationContext对象
         */
//        BeanFactory context = new ClassPathXmlApplicationContext("_application.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("_application.xml");
//        UserService userService = context.getBean(UserService.class);
//        User huixiong = userService.login("huixiong", "123456");
//        System.out.println(huixiong);

        /**
         * 自动注入
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfig.class);// 使用注解的方式加载配置文件
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("huixiong", "123456");
        System.out.println(user);
    }
}
