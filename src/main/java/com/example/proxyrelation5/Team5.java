package com.example.proxyrelation5;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Team5 {

    @Id
    @GeneratedValue
    @Column(name= "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team5")
    private List<Member5> members5 = new ArrayList<>();
}
