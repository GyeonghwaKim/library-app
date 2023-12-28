package com.group.libraryapp.repository;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//@Repository
public class UserJdbcRepository {
    //DB에 sql 보내는 역할
    private final JdbcTemplate jdbcTemplate;
    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist(long id){
        String sql="select * from user where id = ?";

        return jdbcTemplate.query(sql,(rs,rowNum)->0, id).isEmpty();
    }

    public void updateUserName(String name, long id){
        String sql="update user set name =? where id = ?";
        jdbcTemplate.update(sql,name,id);
    }

    public boolean isUserNotExist(String name){
        String readSql="select * from user where id =?";
        return jdbcTemplate
                .query(readSql,(rs,rowNum)-> 0, name)
                .isEmpty();

    }

    public void deleteUser(String name){
        String deleteSql="delete from user where name =?";
        jdbcTemplate.update(deleteSql,name);
    }


    public  void saveUser(String name,Integer age){
        String sql="insert into user(name,age) values (?,?)";
        jdbcTemplate.update(sql,name,age);
    }


    public List<UserResponse> getUserResponse(){
        String sql="select * from user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id=rs.getLong("id");
                String name=rs.getString("name");
                int age=rs.getInt("age");
                return new UserResponse(id,name,age);
            }
        });
    }
}
