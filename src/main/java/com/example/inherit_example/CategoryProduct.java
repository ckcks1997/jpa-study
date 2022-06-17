package com.example.inherit_example;

import javax.persistence.*;

@Entity
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item4")
    private Item4 item4;

    @ManyToOne
    @JoinColumn(name = "category4")
    private Category4 category4;
}
