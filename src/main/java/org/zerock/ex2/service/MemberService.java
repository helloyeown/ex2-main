package org.zerock.ex2.service;

import jakarta.transaction.Transactional;
import org.zerock.ex2.domain.Member;
import org.zerock.ex2.domain.MemberRole;
import org.zerock.ex2.dto.MemberDTO;

import java.util.stream.Collectors;


@Transactional
public interface MemberService {

    MemberDTO get(String email);

    MemberDTO makeSocialMember(String email);


    default MemberDTO entityToDTO (Member member) {


        MemberDTO memberDTO = new MemberDTO(
                member.getEmail(),
                member.getPw(),
                member.getNickname(),
                member.isSocial(),
                member.getMemberRoleList().stream().map(memberRole -> memberRole.name()).collect(Collectors.toList()));

        return memberDTO;

    }

    default Member dtoTOEntity (MemberDTO memberDTO) {


        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .pw(memberDTO.getPw())
                .nickname(memberDTO.getNickname())
                .social(memberDTO.isSocial())
                .build();

        for (String roleName : memberDTO.getRoleNames()) {

            member.addRole(MemberRole.valueOf(roleName));

        }

        return member;
    }



}
