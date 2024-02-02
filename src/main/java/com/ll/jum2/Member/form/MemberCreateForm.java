package com.ll.jum2.Member.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MemberCreateForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password1;

    @NotBlank
    private String password2;

    @Email
    private String email;

}
