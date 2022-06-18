package com.example.type6;


import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Movie6 extends Item6 {
    private String director;
    private String actor;
}
