package com.example.proxyrelation5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Period {

    //part6-값타입
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
