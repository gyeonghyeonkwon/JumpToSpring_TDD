package com.ll.jum2;

import com.ll.jum2.Member.Entity.Member;
import com.ll.jum2.Member.Repostiory.MemberRepository;
import com.ll.jum2.Member.Service.MemberService;
import com.ll.jum2.Question.Entity.Question;
import com.ll.jum2.Question.Repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})

class Jum2ApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 가입")
    void joinTest() {

        // given
        Member member = Member.builder()
                .username("권경현")
                .password("111")
                .email("kyanghyang12@naver.com")
                .build();

        //when
        Member joinMember = this.memberRepository.save(member);

        //then
        assertThat(joinMember.getId()).isNotNull();
        assertThat(joinMember.getCreateDate()).isNotNull();
        assertThat(joinMember.getModifyDate()).isNotNull();
        assertThat(joinMember.getUsername()).isEqualTo("권경현");
        assertThat(joinMember.getEmail()).isEqualTo("kyanghyang12@naver.com");
        assertThat(joinMember.getPassword()).isEqualTo("111");

    }

    @Test
    @DisplayName("회원 가입 아이디 중복 체크 ")

    void joinCheckTest() {
        //given
        Member member = Member.builder()
                .username("권경현")
                .password("111")
                .email("kyanghyang12@naver.com")
                .build();
        memberRepository.save(member);
        //when

        Optional<Member> joinIdCheck = this.memberRepository.findByUsername("권경현");

        //then

        assertTrue(joinIdCheck.isPresent(), "중복된 아이디가 존재합니다.");

    }

    @Test
    @DisplayName("회원 가입 아이디 중복 체크 ")

    void joinPasswordCheck() {
        //given

        //when


        //then


    }



    @Test
    @DisplayName(" 로그인 ")

    void Login() {
        //given

        //when


        //then

    }



    @Test
    @DisplayName("회원 정보 변경")
    void passwordReset() {
        //given
        Optional<Member> _member = this.memberRepository.findById(1L);
        //when
        Member member = _member.orElseThrow(() -> new RuntimeException("가입되어 있는 회원을 찾을 수 없습니다"));

        member.setPassword("222");
        member.setEmail("rnjsrudgus12@naver.com");

        Member ModifyMember = memberRepository.save(member);

        //then
        assertThat(ModifyMember.getPassword()).isEqualTo("222");
//        assertThat(member.getModifyDate()).isEqualTo(member.getCreateDate());
    }



    @Test
    @DisplayName("글 생성")
    void testJpa() {
        Question question = Question.builder()
                .title("ㅋㅋㅋㅋㅋ")
                .content("ㅎㅎㅎㅎㅎ")
                .build();

        this.questionRepository.save(question);
    }



}

