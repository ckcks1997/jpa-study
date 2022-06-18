package com.example.type6;

import com.example.inherit_example.Item4;

import javax.persistence.*;

@Entity
public class CategoryProduct6 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item6")
    private Item6 item6;

    @ManyToOne
    @JoinColumn(name = "category6")
    private Category6 category6;
}
