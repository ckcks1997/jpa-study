package com.example.type6;

import com.example.inherit.BaseEntity;
import com.example.proxyrelation5.Address;

import javax.persistence.*;

@Entity
public class Delivery6 extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery6", fetch = FetchType.LAZY)
    private Order6 order;
}
