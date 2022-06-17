package com.example.inherit_example;

import com.example.inherit.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorColumn //dtype
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//상속관계 설정
public abstract class Item4 extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;
    private Integer stockQuantity;

    @OneToMany(mappedBy = "item4")
    private List<CategoryProduct> category4= new ArrayList<>();
}
