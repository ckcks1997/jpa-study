package com.example.inherit_example;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Album4 extends Item4{
    private String artist;
    private String etc;
}
