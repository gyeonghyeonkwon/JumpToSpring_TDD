package com.ll.jum2.Question.Service;

import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.Question.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question createWrite (String title , String content ) {

    Question question = Question.builder()
            .title(title)
            .content(content)
            .build();

    this.questionRepository.save(question);
    return question;
    }

    public List<Question> getList(){

        return this.questionRepository.findAll();
    }

    public Question getQuestion(Long id) {

        Optional<Question> question = this.questionRepository.findById(id);

        if (question.isPresent()) {
            return question.get();
        } else {
            throw new RuntimeException("게시글을 찾을수 없습니다.");
        }
    }

    //글 수정
    public void modifyWrite(Question question , String title , String content) {

        question.setTitle(title);
        question.setContent(content);

      this.questionRepository.save(question);
    }

    public void deleteWrite(Question question) {

        this.questionRepository.delete(question);
    }
}
