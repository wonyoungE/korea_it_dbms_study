package com.koreait.dbms_study.repository;

import com.koreait.dbms_study.entity.User;
import com.koreait.dbms_study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    public Optional<User> getUserByUserId(Integer userId) {
        return userMapper.getUserByUserId(userId);
    }

    public int editUser(User user) {
        return userMapper.editUser(user);
    }

    public int deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }
}
