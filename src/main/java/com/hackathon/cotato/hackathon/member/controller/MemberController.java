package com.hackathon.cotato.hackathon.member.controller;

import com.hackathon.cotato.hackathon.common.ResponseDto;
import com.hackathon.cotato.hackathon.member.dto.ChangeRoleDto;
import com.hackathon.cotato.hackathon.member.dto.PatchRequestDto;
import com.hackathon.cotato.hackathon.member.service.MemberService;
import com.pyeontect.common.ResponseDto;
import com.pyeontect.member.dto.ChangeRoleDto;
import com.pyeontect.member.dto.PatchRequestDto;
import com.pyeontect.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/info")
    public ResponseEntity<ResponseDto<Object>> getUser(@RequestParam String phone) throws Exception {
        try {
            return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.OK, "User information returned successfully.", memberService.getMember(phone)));
            //return new ResponseEntity<>(memberService.getMember(phone), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }
}
