package com.oss.jceprnic.task06.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="street", nullable = false, unique = true)
    private String street;

    @Column(name="city", nullable = false)
    private String city;

    @Column(name="country", nullable = false)
    private String country;

    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;
}