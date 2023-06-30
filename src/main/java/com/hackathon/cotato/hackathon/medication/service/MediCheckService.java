package com.hackathon.cotato.hackathon.medication.service;

import com.hackathon.cotato.hackathon.medication.domain.MediList;
import com.hackathon.cotato.hackathon.medication.domain.Medication;
import com.hackathon.cotato.hackathon.medication.domain.MedicationRepository;
import com.hackathon.cotato.hackathon.medication.dto.MakeMediListRequestDto;
import com.hackathon.cotato.hackathon.member.domain.Member;
import com.hackathon.cotato.hackathon.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MediCheckService {
    private final MedicationRepository medicationRepository;
    private MemberRepository memberRepository;
    public boolean medicinecheck(MakeMediListRequestDto request) throws Exception {
        try {
            Optional<Member> findmember =memberRepository.findByPhone(request.getMember_phone());
            Optional <Medication> findmedi=medicationRepository.findByMemberAndDrugAndTime(findmember,request.getMedi_name(),request.getMedi_time());

            findmedi.get().updateIsMedi(findmedi.get());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception("Invalid request.");
        }
        return true;

    }

}
