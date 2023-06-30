package com.hackathon.cotato.hackathon.medication.service;

import com.hackathon.cotato.hackathon.medication.domain.*;
import com.hackathon.cotato.hackathon.medication.dto.TodayInfoRequestDto;
import com.hackathon.cotato.hackathon.member.domain.Member;
import com.hackathon.cotato.hackathon.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MediTodayService {

    private final MemberRepository memberRepository;
    private final MedicationRepository medicationRepository;

    private final MediListRepository mediListRepository;

    public TodayInfoRequestDto getMediTodayInfo(String phone) {
        try {
            Optional<Member> member = memberRepository.findByPhone(phone);
            if(member.isEmpty()) {
                throw new Exception("Account not found.");
            }
            else {
                List<Medication> medis = medicationRepository.findByMember(member);
                List<MediListFormat> mediInfoList = new ArrayList<>();

                // 오늘 날짜 구하기
                LocalDate now = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                String formatedNow = now.format(formatter);
                int cnt = 0;

                for(Medication m : medis) {
                    if(m.getDate().equals(formatedNow)) {
                        String medi_name = m.getDrug();
                        String medi_time = m.getTime();
                        Boolean is_medi = m.isMedi();
                        MediListFormat mediListFormat = new MediListFormat(medi_name, medi_time, is_medi);
                        mediInfoList.add(mediListFormat);
                        cnt++;
                    }
                }
                if(cnt == 0) {
                    // mediListRepository를 조회한다
                    List<MediList> mediLists = mediListRepository.findByMember(member);
                    // 만약에 List가 null이면 error 반환(해당일자 X)
                    if(mediLists.isEmpty()) {
                        throw new Exception("No registered medication information.");
                    }
                    // List에 값이 있으면 List에 있는 애들을 하나씩 오늘 날짜로 저장하고, 이거를 다시 리스트로 만들어서 반환한다
                    else {
                        for(MediList ml : mediLists) {
                            Medication medication=Medication.builder()
                                    .drug(ml.getDrug())
                                    .date(formatedNow)
                                    .isMedi(false)
                                    .member(member.get())//get써야 객체가 들어간다
                                    .build();
                            medicationRepository.save(medication);
                        }
                        List<Medication> newmedis = medicationRepository.findByMember(member);

                        for(Medication m : newmedis) {
                            if(m.getDate().equals(formatedNow)) {
                                String medi_name = m.getDrug();
                                String medi_time = m.getTime();
                                Boolean is_medi = m.isMedi();
                                MediListFormat mediListFormat = new MediListFormat(medi_name, medi_time, is_medi);
                                mediInfoList.add(mediListFormat);
                            }
                        }
                    }
                }
                return new TodayInfoRequestDto(mediInfoList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public TodayInfoRequestDto getMediSpecialdayInfo(String date, String phone) {
        try {
            Optional<Member> member = memberRepository.findByPhone(phone);
            if(member.isEmpty()) {
                throw new Exception("Account not found.");
            }
            else {
                List<Medication> medis = medicationRepository.findByMember(member);
                List<MediListFormat> mediInfoList = new ArrayList<>();

                int cnt = 0;

                for(Medication m : medis) {
                    if(m.getDate().equals(date)) {
                        String medi_name = m.getDrug();
                        String medi_time = m.getTime();
                        Boolean is_medi = m.isMedi();
                        MediListFormat mediListFormat = new MediListFormat(medi_name, medi_time, is_medi);
                        mediInfoList.add(mediListFormat);
                        cnt++;
                    }
                }
                if(cnt == 0) {
                    throw new Exception("No medication history for that date.");
                }
                return new TodayInfoRequestDto(mediInfoList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
