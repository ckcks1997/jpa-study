package com.example.inherit_example;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Book4 extends Item4{
    private String author;
    private String isbn;
}
