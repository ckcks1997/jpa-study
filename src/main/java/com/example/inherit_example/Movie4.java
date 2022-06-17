package com.example.inherit_example;


import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Movie4 extends Item4{
    private String director;
    private String actor;
}
