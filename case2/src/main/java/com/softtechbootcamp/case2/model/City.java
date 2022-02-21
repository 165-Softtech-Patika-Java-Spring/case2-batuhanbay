package com.softtechbootcamp.case2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "plate_number", nullable = false)
    private String plateNumber;

    @Column(name = "code_country", nullable = false)
    private String  countryCode;

}
