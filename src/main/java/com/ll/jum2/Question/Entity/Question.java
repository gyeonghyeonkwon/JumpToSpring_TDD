package com.ll.jum2.Question.Entity;

import com.ll.jum2.Member.Entity.Member;
import com.ll.jum2.Answer.Entity.Answer;
import com.ll.jum2.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED) //매개변수가 존재하는  생성자
@NoArgsConstructor(access = PROTECTED) //매개변수가 존재하지 않는 생성자 , 완전한 객체를 생성 하기 위함 , 실수방지
@Setter
@Getter
@ToString(callSuper = true)
public class Question extends BaseEntity {

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE ) //양방향 관계 에서 만 작성
    private List<Answer> answerList;

    @ManyToOne
    private Member author;
}
