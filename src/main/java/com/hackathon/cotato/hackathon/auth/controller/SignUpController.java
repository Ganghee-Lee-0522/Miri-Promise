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
@RequestMapping("/signup")
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

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPhone(@RequestBody LoginRequestDto loginRequestDto) {
        if (memberService.isPhoneDuplicate(loginRequestDto.getPhone())) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.CONFLICT, "Phone number that already exists."));
        }

        return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.OK, "Unique phone number."));
    }

    //private final MemberService memberService;
//    private final SignUpService signUpService;



    /*
     * phone 파라미터로 전화번호 중복여부를 체크하여 반환하는 함수
     */
    /*
    @PostMapping("/verify")
    public ResponseEntity verifyPhoneNum(@RequestParam("phone") VerifyRequestDto requestDto) {
        if(signUpService.verifyPhoneNum(requestDto)) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.CONFLICT, "Phone number that already exists"));
            //return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone number that already exists");
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/submit")
    public ResponseEntity signUp(@RequestBody SignUpRequestDto requestDto) {
        try {
            signUpService.signUp(requestDto);
            return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.CREATED, "Sign up successfully"));
            //return ResponseEntity.status(HttpStatus.CREATED).body("Sign up successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage()));
            //return ResponseEntity.internalServerError().body(ResponseDto.response(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
     */
}
