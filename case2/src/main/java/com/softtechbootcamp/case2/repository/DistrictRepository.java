package com.softtechbootcamp.case2.repository;

import com.softtechbootcamp.case2.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    boolean existsDistrictByNameAndCityId(String name, Long cityId);
    Optional<List<District>> findAllByCityId(Long cityId);
    boolean existsDistrictByIdAndCityId(Long id, Long cityId);
    @Query("SELECT d.name FROM District d where d.id = :id")
    String findNameById(Long id);
}
