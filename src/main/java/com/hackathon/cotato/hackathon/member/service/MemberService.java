package com.hackathon.cotato.hackathon.member.service;

import com.hackathon.cotato.hackathon.member.domain.Member;
import com.hackathon.cotato.hackathon.member.domain.MemberRepository;
import com.hackathon.cotato.hackathon.member.dto.GetMemberInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean isPhoneDuplicate(String phone) {
        try {
            getMember(phone);
            // 이미 존재하는 회원의 경우 Exception이 반환되지 않음
            return true;
        } catch (Exception e) {
            // 존재하지 않는 회원의 경우 Exception이 반환됨
            return false;
        }
    }

    public GetMemberInfoDto getMember(String phone) throws Exception {
        Member member = memberRepository.findByPhone(phone)
                .orElseThrow(() -> new Exception("Account not found."));
        return new GetMemberInfoDto(member);
    }
}
