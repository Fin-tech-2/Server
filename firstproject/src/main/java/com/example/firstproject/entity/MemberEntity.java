package com.example.firstproject.entity;

import com.example.firstproject.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberName;

    @Column
    private String memberPassword;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        return memberEntity;
    }
}
