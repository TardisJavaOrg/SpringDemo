package com.huixiong.services;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MailService {

    public void sendLoginMail(User user) {
        System.out.println(String.format("Hi %s !You are logined in SpringDemo",user.getEmail()));
    }
    @PostConstruct
    public void init(){
        System.out.println("Init mail service");
    }
    @PreDestroy
    public void shutdown(){
        System.out.println("Shutdown mail service");
    }
}
