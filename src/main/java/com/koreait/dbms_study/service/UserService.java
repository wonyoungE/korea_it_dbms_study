package com.koreait.dbms_study.service;

import com.koreait.dbms_study.dto.AddUserReqDto;
import com.koreait.dbms_study.entity.User;
import com.koreait.dbms_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 서비스 -> 비즈니스 로직
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Map<String, String> addUser(AddUserReqDto addUserReqDto) {
        Map<String, String> response = new HashMap<>();
        // addUser()는 User 엔티티를 매개변수로 받음 -> addUserRepDto를 User 객체로 만들어서 넣어주기
        // 여기서 할 수도 있는데 코드가 복잡해지니까 AddUserReqDto 클래스에 메서드 만들어두고 사용
        int result = userRepository.addUser(addUserReqDto.toEntity(addUserReqDto));

        if(result == 1) {   // 성공
            response.put("status", "success");
            response.put("message", "추가 성공");
            return response;
        }
        // 실패
        response.put("status", "failed");
        response.put("message", "추가 실패");
        return response;
    }

    public List<User> getUserList() {
        return userRepository.getUserList();
    }

    public Map<String, Object> getUserByUserId(Integer userId) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> user = userRepository.getUserByUserId(userId);
        // Optional -> <?> 객체가 null인지 아닌지 확인할 수 있다
        
        if(user.isEmpty()) {
            response.put("message", "회원정보가 없습니다.");
            return response;
        }
        // 객체 존재
        response.put("user", user);
        return response;
    }
}
