package com.softtechbootcamp.case2.mapper;

import com.softtechbootcamp.case2.dto.street.StreetRequestDto;
import com.softtechbootcamp.case2.dto.street.StreetResponseDto;
import com.softtechbootcamp.case2.model.Street;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StreetMapper {
    StreetMapper INSTANCE = Mappers.getMapper(StreetMapper.class);
    Street convertToStreet(StreetRequestDto streetRequestDto);
    StreetResponseDto convertToStreetResponseDto(Street street);
    List<StreetResponseDto> convertToStreetResponseDtoList(List<Street> streetList);
}
