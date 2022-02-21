package com.softtechbootcamp.case2.mapper;

import com.softtechbootcamp.case2.dto.district.DistrictRequestDto;
import com.softtechbootcamp.case2.dto.district.DistrictResponseDto;
import com.softtechbootcamp.case2.model.District;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DistrictMapper {
    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    District convertToDistrict(DistrictRequestDto districtRequestDto);
    DistrictResponseDto convertToDistrictResponseDto(District district);
    List<DistrictResponseDto> convertToDistrictResponseDto(List<District> districtList);
}
