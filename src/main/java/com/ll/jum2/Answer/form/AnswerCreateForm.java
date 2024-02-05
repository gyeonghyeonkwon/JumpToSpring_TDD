package com.ll.jum2.Answer.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerCreateForm {

    @NotBlank
    private String content;
}
