package com.ll.jum2.Answer.Entity;


import com.ll.jum2.Member.Entity.Member;
import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class Answer extends BaseEntity {

    @Column
    private String content;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Member author;

}
