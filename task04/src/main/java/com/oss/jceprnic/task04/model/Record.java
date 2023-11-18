package com.oss.jceprnic.task04.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "record")
@Data
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data", nullable = false)
    private String data;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;
}