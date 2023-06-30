package com.hackathon.cotato.hackathon.medication.controller;

import com.hackathon.cotato.hackathon.common.ResponseDto;
import com.hackathon.cotato.hackathon.medication.dto.MakeMediListRequestDto;
import com.hackathon.cotato.hackathon.medication.service.DeleteMediService;
import com.hackathon.cotato.hackathon.medication.service.NewMediService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.Response;

@RequiredArgsConstructor
@RestController
@RequestMapping("miri-promise/medi")
public class DelteMediListController {
    private final DeleteMediService deleteMediService;

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto<Object>> deleteMediList(@RequestBody MakeMediListRequestDto request) throws Exception { // make랑 delete랑 request 항목 같음
        try {
            boolean delete_medi_check=deleteMediService.deletemedi(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, "Internal Server Error"));
        }
        return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.CREATED, "Medication information is deleted."));
    }

}
