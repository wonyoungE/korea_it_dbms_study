package com.koreait.dbms_study.service;

import com.koreait.dbms_study.dto.AddJpaPostReqDto;
import com.koreait.dbms_study.entity.JpaPost;
import com.koreait.dbms_study.repository.JpaPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
