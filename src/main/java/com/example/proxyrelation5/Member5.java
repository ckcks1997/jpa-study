package com.example.proxyrelation5;

import com.example.hellojpa.Locker;
import com.example.hellojpa.RoleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    //같은 임베디드값을 두번 쓰고싶다면..?
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column = @Column(name="WORK_CITY")),
            @AttributeOverride(name="street", column = @Column(name="WORK_STREET")),
            @AttributeOverride(name="zipcode", column = @Column(name="WORK_ZIPCODE"))
    })
    private Address homeAddress2;
    public void setTeam(Team5 team){
        this.team5 = team;
        team.getMembers5().add(this);
    }

}
