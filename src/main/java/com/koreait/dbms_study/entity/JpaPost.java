package com.koreait.dbms_study.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // 엔티티임을 알려줌
@Table(name = "post_tb")    // post_tb랑 연결한다고 알려주기
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JpaPost {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto_increment
    private String postId;
    private String title;
    private String content;
    private Integer userId;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
