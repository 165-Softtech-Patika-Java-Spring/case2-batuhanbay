package com.softtechbootcamp.case2.repository;

import com.softtechbootcamp.case2.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    boolean existsCountryByCode(String code);
    Optional<Country> findCountryByCode(String code);
    boolean existsCountryById(Long id);
}
