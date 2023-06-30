package com.hackathon.cotato.hackathon.auth.service;

import com.hackathon.cotato.hackathon.auth.dto.LoginRequestDto;
import com.hackathon.cotato.hackathon.auth.dto.SignResponseDto;
import com.hackathon.cotato.hackathon.member.domain.Member;
import com.hackathon.cotato.hackathon.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SignResponseDto ptectLogin(LoginRequestDto requestDto) throws Exception {
        Member member = memberRepository.findByPhone(requestDto.getPhone()).orElseThrow(() ->
                new BadCredentialsException("Invalid account information."));

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("Invalid account information.");
        }

        return SignResponseDto.builder()
                .phone(member.getPhone())
                .name(member.getName())
                .token(jwtProvider.createAccessToken(member.getPhone(), member.getName()))
                .build();
    }
}