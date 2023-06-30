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
@Table(name = "TABLE_MEDI")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medi_id")
    private Long id;

    @Column(nullable = false, name = "medi_name")
    private String drug;

    @Column(nullable = false, name = "medi_date")
    private String date;

    @Column(nullable = false, name = "medi_time")
    private String time;

    @Column(nullable = false, name = "is_medi")
    private Boolean isMedi;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Medication updateIsMedi(Medication medication) throws Exception{
        if(isMedi==false){
            this.isMedi=true;
        } else {
            this.isMedi=false;
        }
        return this;
    }

}
