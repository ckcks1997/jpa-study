package com.example.inherit;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Book extends Item2{
    private String author;
    private String isbn;
}
