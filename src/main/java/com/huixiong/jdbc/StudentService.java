package com.huixiong.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
}
