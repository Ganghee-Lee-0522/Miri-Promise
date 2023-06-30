package com.hackathon.cotato.hackathon.medication.dto;

import com.hackathon.cotato.hackathon.medication.domain.MediListFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class TodayInfoRequestDto {
    List<MediListFormat> mediList;

    @Builder
    public TodayInfoRequestDto(List<MediListFormat> mediList) {
        this.mediList = mediList;
    }
}
