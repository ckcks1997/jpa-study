package com.example.type6;

import com.example.proxyrelation5.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Member6 {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String username;


    @Embedded
    private Address address;

   @OneToMany(mappedBy = "member6")
    private List<Order6> orders = new ArrayList<>();
}
