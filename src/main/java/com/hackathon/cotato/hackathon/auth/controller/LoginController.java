package com.hackathon.cotato.hackathon.auth.controller;

import com.pyeontect.auth.dto.LoginRequestDto;
import com.pyeontect.auth.dto.SignResponseDto;
import com.pyeontect.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/ptect")
    public ResponseEntity<SignResponseDto> signIn(@RequestBody LoginRequestDto requestDto) throws Exception {
        return new ResponseEntity<>(loginService.ptectLogin(requestDto), HttpStatus.OK);
    }

}
