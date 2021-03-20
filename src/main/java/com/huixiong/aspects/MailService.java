package com.huixiong.aspects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    @Autowired
    UserService userService;

    public void sendMail(){
        String str = userService.getStr();
        System.out.println("send mail to :"+ str);
    }
}
