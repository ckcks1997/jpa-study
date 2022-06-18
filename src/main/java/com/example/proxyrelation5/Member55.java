package com.example.proxyrelation5;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Member55 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "member_seq_generator")
    private Long id;

    @Column(name = "name")
    private String username;


    @Embedded
    private Address homeAddress;

    //아래는 값 타입이기 때문에 별도의 persist가 필요없다.
    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns = @JoinColumn(name="MEMBER_ID")) //테이블을 생성함
    @Column(name="FOOD_NAME") //String일 경우에만 예외적으로 이름 지정 가능
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="ADDRESS", joinColumns = @JoinColumn(name="MEMBER_ID"))
    private List<Address> addressList = new ArrayList<>();
}
