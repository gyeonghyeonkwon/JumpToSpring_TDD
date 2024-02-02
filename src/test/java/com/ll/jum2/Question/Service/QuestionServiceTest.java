package com.ll.jum2.Question.Service;

import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.Question.Repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    void write(){

        String title = "하하하";
        String content = "호호호";

        Question question = this.questionService.createWrite(title , content);

        assertThat(question.getTitle()).isEqualTo("하하하");
        assertThat(question.getContent()).isEqualTo("호호호");
    }

    @Test
    @DisplayName("게시글 리스트 조회")
    void list() {

        String title =  "하하하";
        String content = "호호호";

        Question question = this.questionService.createWrite(title , content);

        List<Question> questions =  questionService.getList();

        assertEquals(1 , questions.size());

    }
    @Test
    @DisplayName("게시글 찾기")
    void getQuestion(){

        String title =  "하하하";
        String content = "호호호";
        Question question = this.questionService.createWrite(title , content);

        Question question1 = this.questionService.getQuestion(1L);

        assertThat(question.getId()).isEqualTo(question1.getId());

    }

}