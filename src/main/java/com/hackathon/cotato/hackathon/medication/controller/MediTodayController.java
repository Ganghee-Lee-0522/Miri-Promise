package com.hackathon.cotato.hackathon.medication.controller;

import com.hackathon.cotato.hackathon.common.ResponseDto;
import com.hackathon.cotato.hackathon.medication.service.MediTodayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/miri-promise/medi")
public class MediTodayController {

    private final MediTodayService mediTodayService;

    @GetMapping("/today")
    public ResponseEntity<ResponseDto<Object>> getMediToday(@RequestBody String phone) {
        try{
            return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.OK, "Medication information returned successfully.", mediTodayService.getMediTodayInfo(phone)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/{date}")
    public ResponseEntity<ResponseDto<Object>> getMediSpecialday(@RequestParam String date, @RequestBody String phone) {
        try{
            return ResponseEntity.ok().body(ResponseDto.response(HttpStatus.OK, "Medication information returned successfully.", mediTodayService.getMediSpecialdayInfo(date, phone)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.response(HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }
}
