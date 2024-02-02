package com.ll.jum2.Question.Controller;

import com.ll.jum2.Member.Entity.Member;
import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.Question.Form.QuestionCreateForm;
import com.ll.jum2.Question.Service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String questionWrite2(@Valid QuestionCreateForm questionCreateForm , Member author) {

        questionService.createWrite(questionCreateForm.getTitle() , questionCreateForm.getContent());


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

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {

        Question question = this.questionService.getQuestion(id);

        questionService.deleteWrite(question);

        return "redirect:/question/list";
    }
}
