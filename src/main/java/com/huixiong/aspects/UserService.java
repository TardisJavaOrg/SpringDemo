package com.huixiong.aspects;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    String str = new String("huixiong-str");

    public UserService(){
        System.out.println("UserService() init ...");
        System.out.println("UserService() str = "+this.str);
    }
    public String getStr(){
        return str;
    }

}
