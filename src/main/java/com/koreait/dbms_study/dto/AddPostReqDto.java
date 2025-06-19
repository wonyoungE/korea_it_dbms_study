package com.koreait.dbms_study.dto;

import com.koreait.dbms_study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPostReqDto {
    private String title;
    private String content;
    private Integer userId;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .userId(this.userId)
                .build();
    }
}
