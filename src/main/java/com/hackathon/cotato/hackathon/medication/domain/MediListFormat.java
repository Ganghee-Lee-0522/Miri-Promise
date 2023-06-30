package com.hackathon.cotato.hackathon.medication.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MediListFormat {
    private String medi_name;
    private String medi_time;
    private Boolean is_medi;

    @Builder
    public MediListFormat(String medi_name, String medi_time, Boolean is_medi) {
        this.medi_name = medi_name;
        this.medi_time = medi_time;
        this.is_medi = is_medi;
    }
}
