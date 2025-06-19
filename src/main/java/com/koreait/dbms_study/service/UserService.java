package com.koreait.dbms_study.service;

import com.koreait.dbms_study.dto.AddUserReqDto;
import com.koreait.dbms_study.dto.ApiRespDto;
import com.koreait.dbms_study.dto.EditUserReqDto;
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
        int result = userRepository.addUser(addUserReqDto.toEntity());

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

    public ApiRespDto<User> editUser(EditUserReqDto editUserReqDto) {
        // 유효성 검사
        Optional<User> user = userRepository.getUserByUserId(editUserReqDto.getUserId());
        // 해당 id의 유저 없음
        if(user.isEmpty()) {
            return new ApiRespDto<>("해당 유저가 존재하지 않습니다.", null);
        }

        int result = userRepository.editUser(editUserReqDto.toEntity());
        // db에서 update 실패
        if(result == 0) {
            return new ApiRespDto<>("문제가 발생했습니다.", null);
        }
        // 성공
        return new ApiRespDto<>("회원 정보를 수정했습니다.", null);
    }

    public ApiRespDto<Integer> deleteUser(Integer userId) {
        Optional<User> user = userRepository.getUserByUserId(userId);
        if(user.isEmpty()) {
            return new ApiRespDto<>("해당 유저가 존재하지 않습니다.", null);
        }

        // result는 성공한 행수로 return됨 => userId가 List에 들어서 n개를 삭제했다 -> result가 n이 됨
        int result = userRepository.deleteUser(userId);
        if(result == 0) {
            return new ApiRespDto<>("문제가 발생했습니다.", 0);
        }

        return new ApiRespDto<>("성공적으로 삭제되었습니다.", result);
    }
}
