package com.oss.jceprnic.task04.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "record")
@Data
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "electricity_consumption_in_kwh", nullable = false)
    private Long electricityConsumptionInKWh;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    @JsonBackReference
    private Device device;
}
