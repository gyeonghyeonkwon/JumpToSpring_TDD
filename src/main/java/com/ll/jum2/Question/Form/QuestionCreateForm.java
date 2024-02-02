package com.ll.jum2.Question.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionCreateForm {

    @NotBlank
    private String title;

    @NotBlank
    private String content;


}
