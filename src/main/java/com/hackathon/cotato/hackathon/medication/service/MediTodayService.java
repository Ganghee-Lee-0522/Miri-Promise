package com.hackathon.cotato.hackathon.medication.service;

import com.hackathon.cotato.hackathon.medication.domain.MediListFormat;
import com.hackathon.cotato.hackathon.medication.domain.Medication;
import com.hackathon.cotato.hackathon.medication.domain.MedicationRepository;
import com.hackathon.cotato.hackathon.medication.dto.TodayInfoRequestDto;
import com.hackathon.cotato.hackathon.member.domain.Member;
import com.hackathon.cotato.hackathon.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MediTodayService {

    private final MemberRepository memberRepository;
    private final MedicationRepository medicationRepository;

    public TodayInfoRequestDto getMediTodayInfo(String phone) {
        try {
            Optional<Member> member = memberRepository.findByPhone(phone);
            if(member.isEmpty()) {
                throw new Exception("Account not found.");
            }
            else {
                List<Medication> medi = medicationRepository.findByMember(member);
                List<MediListFormat> mediInfoList = new ArrayList<>();

                for(Medication m : medi) {
                    String medi_name = m.getDrug();
                    String medi_time = m.getTime();
                    Boolean is_medi = m.isMedi();
                    MediListFormat mediListFormat = new MediListFormat(medi_name, medi_time, is_medi);
                    mediInfoList.add(mediListFormat);
                }
                return new TodayInfoRequestDto(mediInfoList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
