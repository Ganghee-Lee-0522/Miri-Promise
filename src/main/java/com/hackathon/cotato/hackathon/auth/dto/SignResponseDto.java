package com.hackathon.cotato.hackathon.auth.dto;
import com.hackathon.cotato.hackathon.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponseDto {
    private String phone;
    private String name;
    private String token;

    public SignResponseDto(Member member) {
        this.phone = member.getPhone();
        this.name = member.getName();
    }
}
