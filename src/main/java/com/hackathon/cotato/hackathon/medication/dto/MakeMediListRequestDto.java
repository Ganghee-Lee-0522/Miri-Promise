package com.hackathon.cotato.hackathon.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor //여기에 필드에 쓴 모든생성자만 만들어줌
@NoArgsConstructor //기본 생성자를 만들어줌
@Getter
public class MakeMediListRequestDto {
    @NotBlank(message = "There is no phone")
    private String member_phone;

    @NotBlank(message = "There is no medicine name")
    private String medi_name;

    @NotBlank(message = "There is no medication time")
    private String medi_time;
}
