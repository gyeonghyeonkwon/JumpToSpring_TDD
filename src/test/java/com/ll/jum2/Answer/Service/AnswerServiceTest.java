package com.ll.jum2.Answer.Service;

import com.ll.jum2.Answer.Entity.Answer;
import com.ll.jum2.Answer.Repository.AnswerRepository;
import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.Question.Repository.QuestionRepository;
import com.ll.jum2.Question.Service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@Transactional
class AnswerServiceTest {

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionRepository questionRepository;


    @Test
    @DisplayName("댓글 생성")
    void answerCreate() {

       Question question = Question.builder()
               .title("게시글 제목")
               .content("게시글 내용")
               .build();
        Question q = this.questionService.createWrite(question.getTitle() , question.getContent());

        Answer answer = Answer.builder()
                .content("반가워요")
                .question(question)
                .build();
        this.answerService.AnswerCreate(q , answer.getContent());

        assertThat(answer.getQuestion().getContent()).isEqualTo("게시글 내용");
        assertThat(answer.getQuestion().getId()).isEqualTo(answer.getId());
//        assertThat(question.getAnswerList().size()).isEqualTo(1);

    }
}