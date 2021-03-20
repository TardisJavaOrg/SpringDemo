package com.huixiong.jdbcTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Student getStudentbyId(Integer id) {
        return jdbcTemplate.execute(
                new ConnectionCallback<Student>() {
                    @Override
                    public Student doInConnection(Connection connection) throws SQLException, DataAccessException {
                        PreparedStatement ps = connection.prepareStatement("select * from students where id=?");
                        ps.setObject(1, id);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            return new Student(rs.getString("name"), rs.getInt("age"));
                        }
                        throw new RuntimeException("user not found by id.");
                    }
                });
    }

    @Transactional // 如果直接用到class StudentService 上则所有public 方法都支持事务
    public void updateStudentById(Student student){
        if(1 != jdbcTemplate.update("UPDATE students SET name = ? where id = ?",student.getName(),student.getId())){
            throw  new RuntimeException("User not found by id");
        }
    }
}
