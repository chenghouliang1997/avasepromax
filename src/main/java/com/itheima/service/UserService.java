package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User login(String name,String password){
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.select(name, password);
        sqlSession.close();
        return user;
    }

    public Boolean register(User user){
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = userMapper.selectByName(user.getName());
        if (u == null){
            userMapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return u == null;
    }
}
