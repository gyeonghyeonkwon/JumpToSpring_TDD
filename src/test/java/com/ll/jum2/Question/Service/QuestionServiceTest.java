package com.ll.jum2.Question.Service;

import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.Question.Repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@Transactional
class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    @DisplayName("게시글 작성")
    void write(){

        String title = "하하하1";
        String content = "호호호1";

        Question question = this.questionService.createWrite(title , content);

        assertThat(question.getTitle()).isEqualTo("하하하1");
        assertThat(question.getContent()).isEqualTo("호호호1");
    }

    @Test
    @DisplayName("게시글 리스트 조회")
    void list() {

        String title =  "하하하2";
        String content = "호호호2";

        Question question = this.questionService.createWrite(title , content);

        List<Question> questions =  questionService.getList();

        assertEquals(1 , questions.size());

    }

    @Test
    @DisplayName("게시글 수정")
    void modifyQuestion(){

        String title =  "하하하하4";
        String content = "호호호호4";
        Question question = this.questionService.createWrite(title , content);



        String title2 = "하하";
        String content2 = "호호";
        this.questionService.modifyWrite(null , title2 , content2);

        assertThat(question.getTitle()).isNotEqualTo("하하");
        assertThat(question.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 찾기")
    void getQuestion() {

        String title =  "하하하3";
        String content = "호호호3";
        Question question = this.questionService.createWrite(title , content);

        Question question1 = this.questionService.getQuestion(1L);

        assertThat(question.getId()).isEqualTo(question1.getId());

    }

}