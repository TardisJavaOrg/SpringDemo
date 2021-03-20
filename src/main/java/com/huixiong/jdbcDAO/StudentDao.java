package com.huixiong.jdbcDAO;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentDao extends AbstructDao {

    public Student getById(Integer id){
        return (Student) getJdbcTemplate().execute("select * from students where id = ?", new PreparedStatementCallback() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return new Student(rs.getInt("id"),rs.getString("name"),rs.getInt("age"));
                }
                throw new RuntimeException("user not found by id.");
            }
        });
    }
}
