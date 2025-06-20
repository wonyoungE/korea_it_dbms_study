package com.koreait.dbms_study.mapper;

import com.koreait.dbms_study.dto.GetPostRespDto;
import com.koreait.dbms_study.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    int addPost (Post post);
    List<Post> getPostList();
    Optional<Post> getPostByPostId(Integer postId);
    int editPost(Post post);
    int deletePost(Integer postId);
    Optional<GetPostRespDto> getPostAndUserByPostId(Integer postId);
}
