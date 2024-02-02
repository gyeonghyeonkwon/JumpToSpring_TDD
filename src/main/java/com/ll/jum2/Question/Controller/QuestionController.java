package com.ll.jum2.Question.Controller;

import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.Question.Form.QuestionCreateForm;
import com.ll.jum2.Question.Service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/write")
    public String questionWrite(QuestionCreateForm questionCreateForm) {

        return "domain/question/question/write";
    }

    @PostMapping("/write")
    public String questionWrite2(@Valid QuestionCreateForm questionCreateForm) {

        questionService.createWrite(questionCreateForm.getTitle() , questionCreateForm.getContent() );


        return "redirect:/question/list";
    }

    @GetMapping("/list")
    public String questionList(Model model) {

        List<Question> questionList = this.questionService.getList();

        model.addAttribute("questionList" , questionList);

        return "domain/question/question/list";
    }

    @GetMapping("/detail/{id}")
    public String detailQuestion(@PathVariable Long id , Model model) {

        Question question = this.questionService.getQuestion(id);

        model.addAttribute("question" ,question);

        return "domain/question/question/detail";
    }

    @GetMapping("/modify/{id}")
    public String showModifyQuestion(@PathVariable Long id , QuestionCreateForm questionCreateForm) {

        Question question = this.questionService.getQuestion(id);

        questionCreateForm.setTitle(question.getTitle()); // 기존 제목을 불러옴
        questionCreateForm.setContent(question.getContent()); //기존 내용을 불러옴

        return "domain/question/question/write";
    }


    @PostMapping("/modify/{id}")
    public String modifyQuestion(@PathVariable Long id , @Valid QuestionCreateForm questionCreateForm) {

     Question question = this.questionService.getQuestion(id);

     questionService.modifyWrite(question , questionCreateForm.getTitle() , questionCreateForm.getContent());

        return "redirect:/question/detail/%s".formatted(id);
    }
}
