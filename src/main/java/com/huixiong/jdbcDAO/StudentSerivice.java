package com.huixiong.jdbcDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentSerivice {

    @Autowired
    StudentDao studentDao;

    public Student getById(Integer id){
        return  studentDao.getById(id);
    }
}
