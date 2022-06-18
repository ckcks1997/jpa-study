package com.example.type6;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Book6 extends Item6 {
    private String author;
    private String isbn;
}
