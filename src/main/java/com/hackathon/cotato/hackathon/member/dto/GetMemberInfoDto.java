package com.hackathon.cotato.hackathon.member.dto;

import com.hackathon.cotato.hackathon.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetMemberInfoDto {
    private String phone;
    private String name;

    public GetMemberInfoDto(Member member) {
        this.phone = member.getPhone();
        this.name = member.getName();
    }
}
