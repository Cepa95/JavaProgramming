package com.oss.jceprnic.task04.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device")
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="history", nullable = false)
    private String history;


    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Record> records = new ArrayList<>();
}