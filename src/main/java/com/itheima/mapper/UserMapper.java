package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    User select(@Param("name") String name, @Param("password") String password);

    @Insert("insert into user(name, password) values(#{name},#{password})")
    void add(User user);

    @Select("select * from user where NAME = #{name}")
    User selectByName(String name);
}
