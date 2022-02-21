package com.softtechbootcamp.case2.mapper;

import com.softtechbootcamp.case2.dto.neighborhood.NeighborhoodRequestDto;
import com.softtechbootcamp.case2.dto.neighborhood.NeighborhoodResponseDto;
import com.softtechbootcamp.case2.model.Neighborhood;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface NeighborhoodMapper {
    NeighborhoodMapper INSTANCE = Mappers.getMapper(NeighborhoodMapper.class);
    Neighborhood convertToNeighborhood(NeighborhoodRequestDto neighborhoodRequestDto);
    NeighborhoodResponseDto convertToNeighborhoodResponseDto(Neighborhood neighborhood);
    List<NeighborhoodResponseDto> convertToNeighborhoodResponseDtoList(List<Neighborhood> neighborhoodList);
}
