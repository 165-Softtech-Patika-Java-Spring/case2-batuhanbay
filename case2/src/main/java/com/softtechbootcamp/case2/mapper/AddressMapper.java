package com.softtechbootcamp.case2.mapper;

import com.softtechbootcamp.case2.dto.address.AddressDto;
import com.softtechbootcamp.case2.dto.address.AddressResponseDto;
import com.softtechbootcamp.case2.dto.street.AddressQueryDto;
import com.softtechbootcamp.case2.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    Address convertToAddress(AddressDto addressDto);
    AddressDto convertToAddressDto(Address address);
    AddressResponseDto convertToAddressResponseDto(AddressQueryDto addressQueryDto);
}
