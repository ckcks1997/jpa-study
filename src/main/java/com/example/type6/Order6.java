package com.example.type6;

import com.example.jpashop.domain.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order6 {
    @Id
    @GeneratedValue
    @Column(name="order_id") //스프링부트 쓰면 이게 기본값임..
    private Long id;

    @Column(name="member_id")
    private Long memberId;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name="MEMBER6_ID")
    private Member6 member6;

    @OneToOne
    @JoinColumn(name = "DELIVERY6_ID")
    private Delivery6 delivery6;
}
