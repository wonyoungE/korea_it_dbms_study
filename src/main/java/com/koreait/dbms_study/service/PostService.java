package com.koreait.dbms_study.service;

import com.koreait.dbms_study.dto.AddPostReqDto;
import com.koreait.dbms_study.dto.ApiRespDto;
import com.koreait.dbms_study.dto.EditPostReqDto;
import com.koreait.dbms_study.dto.GetPostRespDto;
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

    public ApiRespDto<Object> addPost(AddPostReqDto addPostReqDto) {
        try {
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
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }
    
    public ApiRespDto<?> getPostList() {
        try {
            List<Post> postList = postRepository.getPostList();
            return new ApiRespDto<>("조회 완료", postList);
        } catch(Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }
    
    public ApiRespDto<Object> getPostByPostId(Integer postId) {
        try {
            Optional<Post> post = postRepository.getPostByPostId(postId);
            if(post.isEmpty()) {
                return new ApiRespDto<>("해당하는 게시물이 없습니다.", null);
            }
            return new ApiRespDto<>("조회 성공", post.get());
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }
    
    public ApiRespDto<?> editPost(EditPostReqDto editPostReqDto) {
        try {
            // 존재하는 게시물인지 확인
            Optional<Post> post = postRepository.getPostByPostId(editPostReqDto.getPostId());
            if(post.isEmpty()) {
                return new ApiRespDto<>("해당하는 게시물이 없습니다.", null);
            }

            int result = postRepository.editPost(editPostReqDto.toEntity());
            if(result != 1) {
                return new ApiRespDto<>("게시물 수정 실패", result);
            }
            return new ApiRespDto<>("게시물 수정 성공", result);
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }

    public ApiRespDto<?> deletePost(Integer postId) {
        try {
            // 존재하지 않는 게시물
            Optional<Post> post = postRepository.getPostByPostId(postId);
            if(post.isEmpty()) {
                return new ApiRespDto<>("존재하지 않는 게시물입니다.", null);
            }

            int result = postRepository.deletePost(postId);
            if(result != 1) {
                return new ApiRespDto<>("게시물 삭제 실패", result);
            }

            return new ApiRespDto<>("게시물 삭제 성공", result);
        } catch (Exception e) {
            return new ApiRespDto<>("문제가 발생했습니다.", e.getMessage());
        }
    }

    public ApiRespDto<GetPostRespDto> getPostAndUserByPostId(Integer postId) {
        Optional<Post> post = postRepository.getPostByPostId(postId);
        if(post.isEmpty()) {
            return new ApiRespDto<>("해당하는 게시물이 없습니다.", null);
        }

        Optional<GetPostRespDto> postRespDto = postRepository.getPostAndUserByPostId(postId);
        if(postRespDto.isEmpty()) {
            return new ApiRespDto<>("오류가 발생했습니다.", null);
        }

        return new ApiRespDto<>("post", postRespDto.get());
    }
}
