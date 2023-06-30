package com.hackathon.cotato.hackathon.auth.controller;

import com.hackathon.cotato.hackathon.auth.dto.LoginRequestDto;
import com.hackathon.cotato.hackathon.auth.dto.SignResponseDto;
import com.hackathon.cotato.hackathon.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("miri-promise/member")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<SignResponseDto> signIn(@RequestBody LoginRequestDto requestDto) throws Exception {
        return new ResponseEntity<>(loginService.getLogin(requestDto), HttpStatus.OK);
    }

}
