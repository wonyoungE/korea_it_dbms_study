package com.koreait.dbms_study.controller;

import com.koreait.dbms_study.dto.AddJpaPostReqDto;
import com.koreait.dbms_study.service.JpaPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// JPA
// 객체 중심의 ORM(Object Relational Mapping)
// 객체 지향 언어(JAVA)의 객체와 관계형 데이터베이스의 테이블간의 매핑을 자동으로 처리
// -> SQL을 직접 쓰지 않고 자바 객체 중심으로 DB 다룸
// Hibernate는 JPA의 구현체
// JPA: 자바에서 ORM을 사용할 수 있게 만든 >인터페이스<
// 장점 => SQL 없이 빠르게 CRUD 가능, 코드량 줄어듦
// 단점 => 복잡한 쿼리는 어렵고 추적/디버깅이 까다롭다. (Hibernate가 구현체라서 우리가 직접 구현한 것이 아니기 때문)
@RestController
@RequestMapping("/jpa/post")
public class JpaPostController {
    @Autowired
    private JpaPostService postJpaService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody AddJpaPostReqDto addJpaPostReqDto) {
        return ResponseEntity.ok(postJpaService.addPost(addJpaPostReqDto));
    }

    @GetMapping("/get/list")
    public ResponseEntity<?> getJpaPostList() {
        return ResponseEntity.ok(postJpaService.getJpaPostList());
    }
}