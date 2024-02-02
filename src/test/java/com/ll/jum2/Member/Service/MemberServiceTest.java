package com.ll.jum2.Member.Service;

import com.ll.jum2.Member.Entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@Transactional    //
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입")
    void createMember() {

        String username = "test";
        String password = "111";
        String email = "test@naver.com";

        Member member = memberService.createMember(username, password, email);

        assertThat(member.getId()).isNotNull();
        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(passwordEncoder.matches(password, member.getPassword())).isTrue();
        assertThat(member.getEmail()).isEqualTo(email);

    }

    @Test
    @DisplayName("아이디 가 중복이 될 시")
    void memberIdCheck() {

        String username = "test1";
        String password = "111";
        String email = "test@naver.com";

         memberService.createMember(username, password, email);


        assertThrows(DataIntegrityViolationException.class, () -> {
            memberService.createMember(username, "222", "333");
        }); //중복 발생 시 예외 처리
    }

    @Test
    @DisplayName("아이디 가 중복이 되지 않을 시")
    void memberIdCheck2() {

        String username = "test2";
        String password = "111";
        String email = "test@naver.com";

         memberService.createMember(username, password, email);


        assertDoesNotThrow(() -> {
                    memberService.createIdCheck("test3");
                }
        );
    }

    @Test
    @DisplayName("아이디를 찾을수 없을때")
    void memberNotFind() {

        String username = "test4";
        String password = "111";
        String email = "test@naver.com";

        memberService.createMember(username, password, email);

        assertThrows(RuntimeException.class , () -> {

            memberService.LoginIdCheck("test5");

        });

    }

    @Test
    @DisplayName("회원 패스 워드 수정 (회원 존재)")

    void memberPasswordChange() {

        String username = "test4";
        String password = "111";
        String email = "test@naver.com";

        Member member = memberService.createMember(username, password, email); //기존 회원 정보 저장


//        memberService.ModifyMember(1L , "222" ); //패스 워드 변경

        assertDoesNotThrow(() -> {

            memberService.ModifyMember(member.getId(), "222"); //회원이 존재 한다면 패스 워드 변경 하고 예외를 던지지 않음
        });


    }
    @Test
    @DisplayName("회원 패스 워드 수정 (회원 존재 x)")

    void memberPasswordChange2() {

        String username = "test5";
        String password = "111";
        String email = "test@naver.com";

        memberService.createMember(username, password, email); //기존 회원 정보 저장


        assertThrows(RuntimeException.class , () -> {

            memberService.ModifyMember(2L , "222" ); //회원이 존재 하지 않는다면 예외를 던짐

        });
    }

    @Test
    @DisplayName("회원 삭제 (회원 존재)")
    void memberDelete() {

        String username = "test5";
        String password = "111";
        String email = "test@naver.com";

        Member member = memberService.createMember(username, password, email); //기존 회원 정보 저장


        assertDoesNotThrow(() -> {

            memberService.deleteMember(member.getId());

        });

    }

    @Test
    @DisplayName("회원 삭제 (회원 존재x)")
    void memberDelete2() {

        String username = "test5";
        String password = "111";
        String email = "test@naver.com";

         memberService.createMember(username, password, email); //기존 회원 정보 저장


        assertThrows(RuntimeException.class, () -> {

            memberService.deleteMember(2L);

        });
    }
}


