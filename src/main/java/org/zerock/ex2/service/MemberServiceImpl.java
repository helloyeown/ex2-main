package org.zerock.ex2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.ex2.domain.Member;
import org.zerock.ex2.domain.MemberRole;
import org.zerock.ex2.dto.MemberDTO;
import org.zerock.ex2.repository.MemberRepository;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements  MemberService{

    private final MemberRepository memberRepository;

    //private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDTO get(String email) {

        Member member = memberRepository.getWithRoles(email);
        return entityToDTO(member);

    }

    @Override
    public MemberDTO makeSocialMember(String email) {

//        Member member = Member.builder()
//                .email(email)
//                .social(true)
//                .nickname("Social Member")
//                .pw(passwordEncoder.encode("1111"))
//                .build();
//
//        member.addRole(MemberRole.USER);
//
//        Member result = memberRepository.save(member);
//
//        return entityToDTO(result);
        return null;
    }
}
















