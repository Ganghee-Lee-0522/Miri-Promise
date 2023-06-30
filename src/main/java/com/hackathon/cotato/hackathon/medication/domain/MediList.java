package com.hackathon.cotato.hackathon.medication.domain;

import com.hackathon.cotato.hackathon.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TABLE_MEDILIST")
public class MediList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medilist_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false,name = "medilist_name")
    private String drug;

    @Column(nullable = false,name = "medilist_time")
    private String time;

}
