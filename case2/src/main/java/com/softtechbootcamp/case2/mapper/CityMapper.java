package com.softtechbootcamp.case2.mapper;

import com.softtechbootcamp.case2.dto.city.CityRequestDto;
import com.softtechbootcamp.case2.dto.city.CityResponseDto;
import com.softtechbootcamp.case2.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    City convertToCity(CityRequestDto cityRequestDto);
    CityResponseDto convertToCityResponseDto(City city);

}
