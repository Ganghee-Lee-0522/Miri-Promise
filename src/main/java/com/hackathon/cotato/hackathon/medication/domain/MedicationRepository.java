package com.hackathon.cotato.hackathon.medication.domain;

import com.hackathon.cotato.hackathon.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByMember(Optional<Member> member);
    Optional<Medication> findByMemberAndDrugAndTime(Optional<Member> member,String drug,String time);
}
