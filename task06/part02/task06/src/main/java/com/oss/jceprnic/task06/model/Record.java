package com.oss.jceprnic.task06.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "record", uniqueConstraints = { @UniqueConstraint(columnNames = {"year_measured", "month_measured", "device_id"})})
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "electricity_consumption_in_kwh", nullable = false)
    private Long electricityConsumptionInKWh;

    @Column(name = "month_measured", nullable = false)
    private Integer monthMeasured;

    @Column(name = "year_measured", nullable = false)
    private Integer yearMeasured;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", nullable = false)
    @JsonBackReference
    private Device device;
}
