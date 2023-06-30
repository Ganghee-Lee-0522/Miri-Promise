package com.hackathon.cotato.hackathon.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TABLE_MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, name = "member_name")
    private String name;

    @Column(unique = true, name = "member_phone")
    private String phone;

    @Column(nullable = false, name = "member_pw")
    private String password;

    @Builder
    public Member(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
    }
}
