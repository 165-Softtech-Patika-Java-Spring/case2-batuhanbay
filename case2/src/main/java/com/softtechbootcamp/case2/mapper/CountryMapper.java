package com.softtechbootcamp.case2.mapper;

import com.softtechbootcamp.case2.dto.country.CountryDto;
import com.softtechbootcamp.case2.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    Country convertToCountry(CountryDto countryDto);
    CountryDto convertToCountryDto(Country country);
}
