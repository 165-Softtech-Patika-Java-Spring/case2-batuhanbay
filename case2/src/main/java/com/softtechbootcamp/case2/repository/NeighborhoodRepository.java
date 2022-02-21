package com.softtechbootcamp.case2.repository;

import com.softtechbootcamp.case2.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
    boolean existsNeighborhoodByNameAndDistrictId(String name, Long districtId);
    Optional<Neighborhood> findNeighborhoodByIdAndDistrictId(Long id, Long districtId);
    Optional<List<Neighborhood>> findAllByDistrictId(Long districtId);
    boolean existsNeighborhoodByIdAndDistrictId(Long id, Long districtId);
    @Query("SELECT n.name FROM Neighborhood n where n.id = :id")
    String findNameById(Long id);
}
