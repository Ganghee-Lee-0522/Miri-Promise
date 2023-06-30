package com.hackathon.cotato.hackathon.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignRequestDto {

    @NotBlank(message = "There is no name")
    private String name;

    @NotBlank(message = "There is no phone")
    private String phone;

    @NotBlank(message = "There is no password")
    private String password;
}
