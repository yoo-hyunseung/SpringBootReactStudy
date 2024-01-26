package com.example.springbootstudy.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing기능을 포함 하는 어노테이션
// JPA Enttiy클래스 들이 BaseTimeEntity를 상속할 경우 createdDate, modifiedDate를 칼럼으로 인식하는 어노테이션
// JPA Auditing -> Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
// Application 클래스에 활성 어노테이션을 써줘야 한다.
public class BaseTimeEntity {
    // 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리 하는 클래스

    // Entity가 생성되어 저장될때 시간이 자동저장
    @CreatedDate
    private LocalDateTime createdDate;

    // 조회한 Entity의 값이 변경될 때 시간이 자동저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
