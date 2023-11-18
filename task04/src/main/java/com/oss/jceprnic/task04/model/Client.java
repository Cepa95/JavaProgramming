package com.oss.jceprnic.task04.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @OneToOne
    @JoinColumn(name = "device_id", unique = true)
    private Device device;

}