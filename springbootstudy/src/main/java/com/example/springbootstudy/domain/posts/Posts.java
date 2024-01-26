package com.example.springbootstudy.domain.posts;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자 자동 추가 lombok
@Getter
@Entity // table mapping
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto Increment db pk-> 자동증가
    private Long id;

    @Column(length = 500 , nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
/*
    @Column 을 사용하지 않아도 필드는 모두 컬럼으로 정의된다 , 사용하는 이유는 컬럼의 세부 설정을 위해 사용
    @Id 는 테이블의 기본키를 나타낸다
 */
    private String author;

    @Builder // 특정 변수만 생성자를 설정 하고 나머지는 기본 값으로 설정 할 수 있다.
    // 사용자는 필요한 속성만 설정하여 객체를 생성할 수 있음
    // 생성자는 값 채울때 어떤값을 채워야하는지 알기 힘들다, 빌더는 어떤값을 채워야 하는지 명시
    public Posts(String title,String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
