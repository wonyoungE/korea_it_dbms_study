package com.koreait.dbms_study.repository;

import com.koreait.dbms_study.entity.JpaPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<JpaPost, Integer> {
    // myBatis와 다르게 mapper 필요없음
    // JpaRepository<T, ID> 상속받고, 순서대로 entity, pk의 자료형
    // 상속받았기 때문에 JpaRepository의 메서드 사용 가능
    
}
