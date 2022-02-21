package com.softtechbootcamp.case2.repository;

import com.softtechbootcamp.case2.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityByPlateNumberAndCountryCode(String plateNumber, String countryCode);
    boolean existsCityByNameAndCountryCode(String name, String countryCode);
    boolean existsCityByPlateNumberAndCountryCode(String plateNumber, String countryCode);
    boolean existsCityById(Long id);
    @Query("SELECT c.name FROM City c where c.id = :id")
    String findNameById(Long id);
}
