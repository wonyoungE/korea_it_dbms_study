package com.koreait.dbms_study.dto;

import com.koreait.dbms_study.entity.JpaPost;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AddJpaPostReqDto {
    private String title;
    private String content;
    private Integer userId;

    public JpaPost toEntity() {
        return JpaPost.builder()
                .title(this.title)
                .content(this.content)
                .userId(this.userId)
                // entity 자체를 던져서 저장하기 때문에 해당 행이 not null이라면 cannot be null 이라고 오류가 뜬다
                .createDt(LocalDateTime.now())
                .build();
    }
}