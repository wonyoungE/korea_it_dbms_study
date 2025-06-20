package com.koreait.dbms_study.repository;

import com.koreait.dbms_study.dto.GetPostRespDto;
import com.koreait.dbms_study.entity.Post;
import com.koreait.dbms_study.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    @Autowired
    private PostMapper postMapper;

    public int addPost(Post post) {
        return postMapper.addPost(post);
    }

    public List<Post> getPostList() {
        return postMapper.getPostList();
    }

    public Optional<Post> getPostByPostId(Integer postId) {
        return postMapper.getPostByPostId(postId);
    }

    public int editPost(Post post) {
        return postMapper.editPost(post);
    }

    public int deletePost(Integer postId) {
        return postMapper.deletePost(postId);
    }

    public Optional<GetPostRespDto> getPostAndUserByPostId(Integer postId) {
        return postMapper.getPostAndUserByPostId(postId);
    }
}
