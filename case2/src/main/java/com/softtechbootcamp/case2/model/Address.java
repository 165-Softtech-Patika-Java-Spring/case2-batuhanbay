package com.softtechbootcamp.case2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "building_no", nullable = false)
    private String buildingNo;

    @Column(name = "door_no", nullable = false)
    private String doorNo;

    @Column(name = "id_country", nullable = false)
    private Long countryId;

    @Column(name = "id_city", nullable = false)
    private Long cityId;

    @Column(name = "id_district", nullable = false)
    private Long districtId;

    @Column(name = "id_neighborhood", nullable = false)
    private Long neighborhoodId;

    @Column(name = "id_street", nullable = false)
    private Long streetId;

}
