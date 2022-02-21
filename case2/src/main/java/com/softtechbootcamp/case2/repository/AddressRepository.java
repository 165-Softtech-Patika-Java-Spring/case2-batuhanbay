package com.softtechbootcamp.case2.repository;

import com.softtechbootcamp.case2.dto.address.AddressQueryDto;
import com.softtechbootcamp.case2.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findAddressById(Long id);


    @Query(value = "SELECT country.name as countryName,"
            + " city.name as cityName,"
            + " district.name as districtName,"
            + " neighborhood.name as neighborhoodName,"
            + " street.name as streetName,"
            + " address.building_no as buildingNo,"
            + " address.door_no as doorNo"
            + " FROM Address address "
            + " LEFT OUTER JOIN Country country ON (country.id = address.id_country) "
            + " LEFT OUTER JOIN  City city ON (city.id = address.id_city)"
            + " LEFT OUTER JOIN  District district ON (district.id = address.id_district)"
            + " LEFT OUTER JOIN  Neighborhood neighborhood ON (neighborhood.id = address.id_neighborhood)"
            + " LEFT OUTER JOIN  Street street ON (street.id = address.id_street)"
            + " WHERE address.id = :id", nativeQuery = true)
    AddressQueryDto findAddressByIdWithNames(@Param("id") Long id);
}