package com.example.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column(name="order_id") //스프링부트 쓰면 이게 기본값임..
    private Long id;

    @Column(name="member_id")
    private Long memberId;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
