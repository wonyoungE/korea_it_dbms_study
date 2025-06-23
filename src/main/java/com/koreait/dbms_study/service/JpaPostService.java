package com.koreait.dbms_study.service;

import com.koreait.dbms_study.dto.AddJpaPostReqDto;
import com.koreait.dbms_study.dto.ApiRespDto;
import com.koreait.dbms_study.dto.EditPostReqDto;
import com.koreait.dbms_study.entity.JpaPost;
import com.koreait.dbms_study.repository.JpaPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JpaPostService {
    @Autowired
    private JpaPostRepository postJpaRepository;

    public JpaPost addPost(AddJpaPostReqDto addJpaPostReqDto) {
        // myBatis와 다르게 추가한 객체 자체를 return함
        // myBatis는 행 수 return
        return postJpaRepository.save(addJpaPostReqDto.toEntity());
    }

    public List<JpaPost> getJpaPostList() {
        return postJpaRepository.findAll();
    }

//    public JpaPost getJpaPostByPostId(Integer postId) {
////        return postJpaRepository.findById(postId).orElseThrow(() -> new Exception("게시글이 없습니다."));
//    }

    public ApiRespDto<?> editPost(EditPostReqDto editPostReqDto) {
        Optional<JpaPost> optionalPost = postJpaRepository.findById(editPostReqDto.getPostId());
        if(optionalPost.isEmpty()) {
            return new ApiRespDto<>("해당 게시물이 존재하지 않습니다.", null);
        }

        try {
            JpaPost jpaPost = optionalPost.get();
            jpaPost.setTitle(editPostReqDto.getTitle());
            jpaPost.setContent(editPostReqDto.getContent());
            jpaPost.setUpdateDt(LocalDateTime.now());
            postJpaRepository.save(jpaPost);

            return new ApiRespDto<>("게시물 수정 완료!", jpaPost);
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }

    public ApiRespDto<?> removePost(Integer postId) {
        Optional<JpaPost> optionalPost = postJpaRepository.findById(postId);
        if(optionalPost.isEmpty()) {
            return new ApiRespDto<>("해당 게시물이 존재하지 않습니다.", null);
        }

        try {
            postJpaRepository.deleteById(postId);
        } catch(Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }

        return new ApiRespDto<>("삭제 성공!", null);
    }
}
