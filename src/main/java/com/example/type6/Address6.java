package com.example.type6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address6 {
    //주소
    private String city;
    private String street;
    private String zipcode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address6 address6 = (Address6) o;
        return Objects.equals(getCity(), address6.getCity()) && Objects.equals(getStreet(), address6.getStreet()) && Objects.equals(getZipcode(), address6.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
