package com.huixiong.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private MailService mailService;

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("huixiong", "123456"));
    }

    public User login(String email, String password) {
        for (User user :
                users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login Failed.");
    }
}
