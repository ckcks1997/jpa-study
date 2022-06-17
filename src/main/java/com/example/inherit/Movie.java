package com.example.inherit;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Movie extends Item2{
    private String director;
    private String actor;
}
