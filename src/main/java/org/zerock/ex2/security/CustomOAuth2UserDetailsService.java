package org.zerock.ex2.security;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.ex2.domain.Member;
import org.zerock.ex2.domain.MemberRole;
import org.zerock.ex2.dto.MemberDTO;
import org.zerock.ex2.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
 
@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService {
 
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
 
   
  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
 
      log.info("userRequest....");
      log.info(userRequest);
 
      log.info("oauth2 user.....................................");
 
      ClientRegistration clientRegistration = userRequest.getClientRegistration();
      String clientName = clientRegistration.getClientName();
 
      log.info("NAME: "+clientName);
      OAuth2User oAuth2User = super.loadUser(userRequest);
      Map<String, Object> paramMap = oAuth2User.getAttributes();
 
      String email = null;
 
      switch (clientName){
          case "kakao":
              email = getKakaoEmail(paramMap);
              break;
      }
 
      log.info("===============================");
      log.info(email);
      log.info("===============================");
 
      MemberDTO memberDTO =  generateDTO(email, paramMap);
 
      log.info("MEMBERDTO: " + memberDTO);
 
      return memberDTO;
  }
 
  private MemberDTO generateDTO(String email, Map<String, Object> params){
 
        Member member = memberRepository.getWithRoles(email);
 
        //데이터베이스에 해당 이메일을 사용자가 없다면
        if(member == null){
            //회원 추가 -- mid는 이메일 주소/ 패스워드는 1111
            Member socialMember = Member.builder()
                    .email(email)
                    .pw(passwordEncoder.encode("1111"))
                    .nickname("Social")
                    .email(email)
                    .social(true)
                    .build();
            socialMember.addRole(MemberRole.USER);
            memberRepository.save(socialMember);
 
            //MemberDTO 구성 및 반환
            MemberDTO memberDTO =
                    new MemberDTO(email, "1111", "Social", true, java.util.List.of("USER"));
            memberDTO.setProps(params);     // 카카오가 전달한 모든 정보를 세팅
 
            return memberDTO;

        }else {
 
            MemberDTO memberDTO =
                    new MemberDTO(
                            member.getEmail(),
                            member.getPw(),
                            member.getNickname(),
                            member.isSocial(),
                            member.getMemberRoleList().stream().map(role -> role.name()).collect(Collectors.toList()));
 
            return memberDTO;
        }
    }



 
  private String getKakaoEmail(Map<String, Object> paramMap){
 
    log.info("KAKAO-----------------------------------------");
 
    Object value = paramMap.get("kakao_account");
 
    log.info(value);
 
    LinkedHashMap accountMap = (LinkedHashMap) value;
 
    String email = (String)accountMap.get("email");
 
    log.info("email..." + email);
 
    return email;
  }
}


