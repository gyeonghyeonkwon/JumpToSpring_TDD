package com.ll.jum2.Answer.Service;

import com.ll.jum2.Answer.Repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public String test() {

        return"";
    }
}
