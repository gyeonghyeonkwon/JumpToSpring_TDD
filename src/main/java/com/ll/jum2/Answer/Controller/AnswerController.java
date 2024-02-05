package com.ll.jum2.Answer.Controller;

import com.ll.jum2.Answer.Service.AnswerService;
import com.ll.jum2.Answer.form.AnswerCreateForm;
import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.Question.Service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String postAnswer(@PathVariable Long id , @Valid AnswerCreateForm answerCreateForm ) {

        Question question = questionService.getQuestion(id);

        this.answerService.AnswerCreate(question , answerCreateForm.getContent());
        return "redirect:/question/detail/%s".formatted(id);
    }


}
