package com.hackathon.cotato.hackathon.medication.controller;


import com.hackathon.cotato.hackathon.common.ResponseDto;
import com.hackathon.cotato.hackathon.medication.dto.MakeMediListRequestDto;
import com.hackathon.cotato.hackathon.medication.service.NewMediService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("miri-promise/medi")
public class MakeMedListController {
    private final NewMediService newMediService;

    @PostMapping("/new")
    public ResponseEntity<ResponseDto<Object>> makeMediList(@RequestBody MakeMediListRequestDto request) throws Exception {
        try {
            boolean register_medi_check=newMediService.registernewmedi(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, "Internal Server Error"));
        }
        return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.CREATED, "Medication information registered."));
    }
}
