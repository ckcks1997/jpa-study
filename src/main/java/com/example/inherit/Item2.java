package com.example.inherit;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@DiscriminatorColumn //dtype
@Inheritance(strategy = InheritanceType.JOINED)//상속관계 설정
public abstract class Item2 extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;
}
