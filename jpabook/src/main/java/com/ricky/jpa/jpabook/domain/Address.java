package com.ricky.jpa.jpabook.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(callSuper = true)
public class Address {

    private String city;
    private String street;
    private String zipcode;


}
