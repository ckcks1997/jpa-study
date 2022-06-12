package com.example.jpashop.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Members {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
