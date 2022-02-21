package com.softtechbootcamp.case2.repository;

import com.softtechbootcamp.case2.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityByPlateNumberAndCountryCode(String plateNumber, String countryCode);
    boolean existsCityByNameAndCountryCode(String name, String countryCode);
    boolean existsCityByPlateNumberAndCountryCode(String plateNumber, String countryCode);
    boolean existsCityById(Long id);
}
