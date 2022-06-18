package com.example.type6;

import com.example.inherit.BaseEntity;
import com.example.inherit_example.CategoryProduct;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorColumn //dtype
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//상속관계 설정
public abstract class Item6 extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;
    private Integer stockQuantity;

    @OneToMany(mappedBy = "item6")
    private List<CategoryProduct6> category6= new ArrayList<>();
}
