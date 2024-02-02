package com.ll.jum2.Answer.Service;

import com.ll.jum2.Answer.Entity.Answer;
import com.ll.jum2.Answer.Repository.AnswerRepository;
import com.ll.jum2.Question.Entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void AnswerCreate(Question question , String content) {

        Answer answer = Answer.builder()
                .content(content)
                .question(question)
                .build();

        this.answerRepository.save(answer);
    }
}
