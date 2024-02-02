package com.ll.jum2.Member.Service;

import com.ll.jum2.Member.Entity.Member;
import com.ll.jum2.Member.Repostiory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    //계정 생성
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public Member createMember (final String username ,final String password , final String email) {

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        this.memberRepository.save(member);

        return member;
    }
    //회원 가입 아이디 중복
    public void createIdCheck(final String username) {

        Optional<Member> memberOptional = this.memberRepository.findByUsername(username);

        if(memberOptional.isPresent()) {
            throw new DuplicateFormatFlagsException("회원 가입 아이디 중복 예외 처리 ");
        }

    }
   // id 찾기
    public void LoginIdCheck(final String username ) {

        Optional<Member> memberOptional = memberRepository.findByUsername(username);

        if(memberOptional.isEmpty()) {
            throw new RuntimeException("아이디 를 찾을 수 없습니다.");
        }
    }

    //회원 패스 워드 변경 수정
    public void ModifyMember(Long id  , String password) {

        Optional<Member> memberOptional = memberRepository.findById(id);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            member.setPassword(passwordEncoder.encode(password));

            this.memberRepository.save(member);
        }
    else {
        throw new RuntimeException("회원을 찾을수 없습니다.");
        }
    }

    public void deleteMember (Long id) {

        Optional<Member> memberOptional = memberRepository.findById(id);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            this.memberRepository.delete(member);

        }
        else {
            throw new RuntimeException("회원을 찾을수 없습니다.");
        }
    }

    public Member getMember(Long id ) {

        return memberRepository.findById(id)
                .orElseThrow(() -> new DuplicateFormatFlagsException("회원을 찾을 수 없습니다."));
    }
}
