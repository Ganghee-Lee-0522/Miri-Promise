package com.hackathon.cotato.hackathon.medication.service;

import com.hackathon.cotato.hackathon.medication.domain.MediList;
import com.hackathon.cotato.hackathon.medication.domain.MediListRepository;
import com.hackathon.cotato.hackathon.medication.domain.Medication;
import com.hackathon.cotato.hackathon.medication.dto.MakeMediListRequestDto;
import com.hackathon.cotato.hackathon.member.domain.Member;
import com.hackathon.cotato.hackathon.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteMediService {
    private final MediListRepository mediListRepository;
    private final MemberRepository memberRepository;
    public boolean deletemedi(MakeMediListRequestDto request) throws Exception {
        try {
            Optional<Member> findmemberfordel =memberRepository.findByPhone(request.getMember_phone());

            MediList mediList=MediList.builder()
                    .member(findmemberfordel.get())
                    .drug(request.getMedi_name())
                    .time(request.getMedi_time())
                    .build();
            mediListRepository.delete(mediList);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception("Invalid request.");
        }
        return true;

    }
}
