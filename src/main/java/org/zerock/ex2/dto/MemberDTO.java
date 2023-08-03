package org.zerock.ex2.dto;

import lombok.Data;

import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
@ToString
// UserDetails를 반환하기위한 Social Login했을때 반환 하기위한
public class MemberDTO extends User implements OAuth2User {

    private String email;
    private String pw;

    private String nickname;

    private boolean social;

    private List<String> roleNames = new ArrayList<>();



    public MemberDTO(String email, String pw, String nickname, boolean social, List<String> roleNames){

        super(email,pw, roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList()));

        this.email = email;
        this.pw = pw;
        this.roleNames = roleNames;
        this.nickname = nickname;
        this.social = social;

    }

    public Map<String, Object> getClaims() {
        // JWT 만들때 필요한 정보들만 넣어 놓는 메소드
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("pw", pw);
        map.put("nickname", nickname);
        map.put("social", social);
        map.put("roleNames", roleNames);
        
        return map;

    }

    // 카카오가 전달한 모든 정보를 세팅
    public void setProps(Map<String, Object> map) {
        
    }

    // OAuth2User 처리시에 들어가는 메소드 
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return this.email;
    }
}
