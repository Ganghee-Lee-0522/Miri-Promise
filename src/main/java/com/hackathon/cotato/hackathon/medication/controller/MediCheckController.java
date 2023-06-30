package com.hackathon.cotato.hackathon.medication.controller;

import com.hackathon.cotato.hackathon.common.ResponseDto;
import com.hackathon.cotato.hackathon.medication.dto.MakeMediListRequestDto;
import com.hackathon.cotato.hackathon.medication.service.MediCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("miri-promise/medi")
public class MediCheckController {
    private final MediCheckService mediCheckService;

    @PatchMapping("/check")
    public ResponseEntity<ResponseDto<Object>> mediCheck(@RequestBody MakeMediListRequestDto request) throws Exception { // make랑 request 항목 같음
        try {
            boolean medi_check=mediCheckService.medicinecheck(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage()));
        }
        return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.CREATED, "Register medicine successfully."));
    }

}
