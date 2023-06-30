package com.hackathon.cotato.hackathon.auth.controller;

import com.hackathon.cotato.hackathon.auth.dto.LoginRequestDto;
import com.hackathon.cotato.hackathon.auth.dto.SignRequestDto;
import com.hackathon.cotato.hackathon.auth.service.SignUpService;
import com.hackathon.cotato.hackathon.common.ResponseDto;
import com.hackathon.cotato.hackathon.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/miri-promise/member/signup")
public class SignUpController {

    private final SignUpService signUpService;
    private final MemberService memberService;

    @PostMapping("/submit")
    public ResponseEntity<ResponseDto<Object>> signUp(@RequestBody SignRequestDto request) throws Exception {
        try {
            //return new ResponseEntity<>(signUpService.register(request), HttpStatus.OK);
            signUpService.register(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage()));
        }
        return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.CREATED, "Sign up successfully."));
    }

    // 프론트에서 해당 기능 안쓴다고 함
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPhone(@RequestBody LoginRequestDto loginRequestDto) {
        if (memberService.isPhoneDuplicate(loginRequestDto.getPhone())) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.CONFLICT, "Phone number that already exists."));
        }

        return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.OK, "Unique phone number."));
    }
}
