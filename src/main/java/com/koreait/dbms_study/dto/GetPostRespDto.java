package com.koreait.dbms_study.dto;

import com.koreait.dbms_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPostRespDto {
    private Integer postId;
    private String title;
    private String content;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
    private User user;
}