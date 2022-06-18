package com.example.jpql7;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Entity
@NoArgsConstructor
public class Member7 {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @Enumerated(EnumType.STRING)
    private MemberType7 memberType7;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team7 team7;



    public void changeTeam7(Team7 team7){
        this.team7 = team7;
        team7.getMembers7().add(this);
    }

    @Override
    public String toString() {
        return "Member7{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", memberType7=" + memberType7 +
                '}';
    }
}
