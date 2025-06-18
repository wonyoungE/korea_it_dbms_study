package com.koreait.dbms_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Integer userId; // null 값이 올 수도 있기 때문에 wrapper 클래스의 객체로 받기
    private String username;
    private String email;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
