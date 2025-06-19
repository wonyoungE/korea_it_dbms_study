package com.koreait.dbms_study.mapper;

import com.koreait.dbms_study.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    // Mapper -> 메서드 구현 안하고 선언만
    // 구현은 user_mapper.xml 에서 SQL문으로 한다.

    int addUser(User user);  // insert 결과가 성공:1 실패:0
    List<User> getUserList();
    // Optional -> User가 null인지 아닌지 확인하는 메서드가 있음
    Optional<User> getUserByUserId(Integer userId);   // int로 하면 userId를 안줬는데 0이 올 수도 있기 때문에..
    int editUser(User user);
    int deleteUser(Integer userId);
}
