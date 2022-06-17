package com.example.inherit_example;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category4 {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category4")
    private List<CategoryProduct> CategoryProduct4 = new ArrayList<>();
}
