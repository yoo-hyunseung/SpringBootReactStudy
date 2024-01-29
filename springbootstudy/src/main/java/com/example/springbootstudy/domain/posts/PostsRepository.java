package com.example.springbootstudy.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    // Posts 클래스로 database 접근하기위해 <클래스명, PK>
    // Entity 클래스와 같은 디렉토리에 존재해야한다 @Repository 안써도 됨다.
    // 자동으로 빈을 등록해서 안써도된다

    @Query(value = "select * from posts order by id desc" , nativeQuery = true)
    List<Posts> findAllDesc();

}
