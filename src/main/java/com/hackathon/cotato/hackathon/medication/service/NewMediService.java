package com.hackathon.cotato.hackathon.medication.service;

import com.hackathon.cotato.hackathon.medication.domain.MediList;
import com.hackathon.cotato.hackathon.medication.domain.Medication;
import com.hackathon.cotato.hackathon.medication.dto.MakeMediListRequestDto;
import com.hackathon.cotato.hackathon.member.domain.Member;
import com.hackathon.cotato.hackathon.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NewMediService {
    private final MediListRepository mediListRepository;
    private final MedicationRepository medicationRepository;
    private MemberRepository memberRepository;

    public boolean registernewmedi(MakeMediListRequestDto request) throws Exception {
        try {
            // 오늘 날짜 구하기
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            String formatedNow = now.format(formatter);

            // 받은 phone num으로 멤버 id찾기
            Optional<Member> findmember =memberRepository.findByPhone(request.getMember_phone());

            Medication medication=Medication.builder()
                    .drug(request.getMedi_name())
                    .date(formatedNow)
                    .isMedi(false)
                    .member(findmember.get())//get써야 객체가 들어간다
                    .build();
            medicationRepository.save(medication);

            MediList mediList=MediList.builder()
                    .member()
                    .drug(request.getMedi_name())
                    .time(request.getMedi_time())
                    .build();
            mediListRepository.save(mediList);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception("Invalid request.");
        }
        return true;
    }

}
