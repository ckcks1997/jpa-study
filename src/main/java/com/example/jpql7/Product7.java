package com.example.jpql7;


import com.example.proxyrelation5.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product7 {
    @Id
    @GeneratedValue
    private Long id;

    private int orderAmount;

    @Embedded
    private Address7 address7;
    @ManyToOne
    @JoinColumn(name = "PRODUCT7_ID")
    private Product7 product7;
}
