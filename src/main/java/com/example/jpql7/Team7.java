package com.example.jpql7;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team7 {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team7")
    private List<Member7> members7 = new ArrayList<>();

    @Override
    public String toString() {
        return "Team7{" +
                "id=" + id +
                ", username='" + name + '\'' +

                '}';
    }
}
