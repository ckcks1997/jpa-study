package com.example.type6;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Album6 extends Item6 {
    private String artist;
    private String etc;
}
