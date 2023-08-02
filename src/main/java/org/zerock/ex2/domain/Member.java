package org.zerock.ex2.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString (exclude = "memberRoleList")
@Table(name = "t_member")
public class Member {

    @Id
    private String email;

    private String pw;

    private String nickname;

    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole){

        memberRoleList.add(memberRole);
    }

    public void clearRole(){
        memberRoleList.clear();
    }
}
