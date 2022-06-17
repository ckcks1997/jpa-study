package com.example.proxyrelation5;

import com.example.hellojpa.Locker;
import com.example.hellojpa.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@SequenceGenerator(name = "member_seq_generator",
sequenceName = "member_seq")
//@Table(name="Member")
public class Member5 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "member_seq_generator")
    private Long id;

    @Column(name = "name")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID") //실제 회원 테이블의 FK와 연결
    private Team5 team5;


    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;


    public void setTeam(Team5 team){
        this.team5 = team;
        team.getMembers5().add(this);
    }

}
