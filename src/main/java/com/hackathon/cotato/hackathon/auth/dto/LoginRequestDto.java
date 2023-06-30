package com.hackathon.cotato.hackathon.auth.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "There is no phone")
    private String phone;

    @NotBlank(message = "There is no password")
    private String password;
}
