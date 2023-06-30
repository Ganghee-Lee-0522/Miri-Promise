package com.hackathon.cotato.hackathon.medication.domain;

import com.hackathon.cotato.hackathon.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MediListRepository extends JpaRepository<MediList, Long> {
    List<MediList> findByMember(Optional<Member> member);

}
