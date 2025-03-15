package com.example.mymultitenant.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "broker")
public class Broker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "broker_name")
    private String brokerName;

    @Column(name = "broker_number")
    private String brokerNumber;

    @Column(name = "broker_gst_number")
    private String brokerGSTNumber;

    @Column(name = "location")
    private String location;
}
