package com.koreait.dbms_study.controller;

import com.koreait.dbms_study.dto.AddUserReqDto;
import com.koreait.dbms_study.dto.EditUserReqDto;
import com.koreait.dbms_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// mybatis
// SQL 중심의 데이터 접근 프레임워크
// -> 내가 직접 SQL문 작성하고, 그 결과를 JAVA 객체로 매핑
// SQL을 직접 짜기 때문에 완전히 통제 가능(자유도 높음, JPA는 간단한 거 밖에 못함), 대신 코드가 복잡하고 반복적
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 데이터를 가져오기 -> get

    // 데이터를 넣기 -> post
    // wildcard(?) -> 타입 제한 없이 받을 수 있음
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody AddUserReqDto addUserReqDto) {
        return ResponseEntity.ok(userService.addUser(addUserReqDto));
    }

    @GetMapping("/get/list")
    public ResponseEntity<?> getUserList() {
        return ResponseEntity.ok().body(userService.getUserList());
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    // 요청 메서드 중 DELETE, PUT 있는데 POST로
    // 왜? 1. 보안상(csrf) 2. 호환성
    // 일부 브라우저 또는 서버가 PUT, DELETE를 완벽하게 지원하지 않음
    // HTML <form>가 GET, POST만 지원
    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody EditUserReqDto editUserReqDto) {
        return ResponseEntity.ok(userService.editUser(editUserReqDto));
    }

    // post는 body - param 다 받을 수 있음
    // get은 param만 가능
    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Integer userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
