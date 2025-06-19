package com.koreait.dbms_study.service;

import com.koreait.dbms_study.dto.AddPostReqDto;
import com.koreait.dbms_study.dto.ApiRespDto;
import com.koreait.dbms_study.dto.EditPostReqDto;
import com.koreait.dbms_study.entity.Post;
import com.koreait.dbms_study.entity.User;
import com.koreait.dbms_study.repository.PostRepository;
import com.koreait.dbms_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<Integer> addPost(AddPostReqDto addPostReqDto) {
        // 존재하는 회원인지 확인
        Optional<User> user = userRepository.getUserByUserId(addPostReqDto.getUserId());
        if(user.isEmpty()) {
            return new ApiRespDto<>("존재하지 않는 회원입니다.", null);
        }

        int result = postRepository.addPost(addPostReqDto.toEntity());
        if(result == 0) {
            return new ApiRespDto<>("게시물 등록 실패", result);
        }

        return new ApiRespDto<>("게시물 등록 성공", result);
    }
    
    public List<Post> getPostList() {
        return postRepository.getPostList();
    }
    
    public ApiRespDto<Post> getPostByPostId(Integer postId) {
        Optional<Post> post = postRepository.getPostByPostId(postId);
        if(post.isEmpty()) {
            return new ApiRespDto<>("해당하는 게시물이 없습니다.", null);
        }
        
        return new ApiRespDto<>("post", post.get());
    }
    
    public ApiRespDto<Integer> editPost(EditPostReqDto editPostReqDto) {
        // 존재하는 게시물인지 확인
        Optional<Post> post = postRepository.getPostByPostId(editPostReqDto.getPostId());
        if(post.isEmpty()) {
            return new ApiRespDto<>("해당하는 게시물이 없습니다.", null);
        }
        
        int result = postRepository.editPost(editPostReqDto.toEntity());
        if(result == 0) {
            return new ApiRespDto<>("게시물 수정 실패", result);
        }

        return new ApiRespDto<>("게시물 수정 성공", result);
    }

    public ApiRespDto<Integer> deletePost(Integer postId) {
        // 존재하지 않는 게시물입니다.
        Optional<Post> post = postRepository.getPostByPostId(postId);
        if(post.isEmpty()) {
            return new ApiRespDto<>("존재하지 않는 게시물입니다.", null);
        }

        int result = postRepository.deletePost(postId);
        if(result == 0) {
            return new ApiRespDto<>("게시물 삭제 실패", result);
        }

        return new ApiRespDto<>("게시물 삭제 성공", result);
    }
}
