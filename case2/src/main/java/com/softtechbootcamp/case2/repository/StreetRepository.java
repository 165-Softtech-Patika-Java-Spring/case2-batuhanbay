package com.softtechbootcamp.case2.repository;

import com.softtechbootcamp.case2.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
    boolean existsStreetByNameAndNeighborhoodId(String name, Long neighborhoodId);
    Optional<Street> findStreetByIdAndNeighborhoodId(Long id, Long neighborhoodId);
    Optional<List<Street>> findAllByNeighborhoodId(Long neighborhoodId);
    boolean existsStreetByIdAndNeighborhoodId(Long id, Long neighborhoodId);
    @Query("SELECT s.name FROM Street s where s.id = :id")
    String findNameById(Long id);
}