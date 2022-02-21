package com.softtechbootcamp.case2.service;

import com.softtechbootcamp.case2.dto.address.AddressDto;
import com.softtechbootcamp.case2.dto.address.AddressResponseDto;
import com.softtechbootcamp.case2.dto.street.AddressQueryDto;
import com.softtechbootcamp.case2.enums.ErrorMessage;
import com.softtechbootcamp.case2.exceptions.EntityNotFoundException;
import com.softtechbootcamp.case2.mapper.AddressMapper;
import com.softtechbootcamp.case2.model.Address;
import com.softtechbootcamp.case2.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {
    AddressRepository addressRepository;
    CountryRepository countryRepository;
    CityRepository cityRepository;
    DistrictRepository districtRepository;
    NeighborhoodRepository neighborhoodRepository;
    StreetRepository streetRepository;

    public AddressDto saveAddress(AddressDto addressDto){
        if (!countryRepository.existsCountryById(addressDto.getCountryId())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_COUNTRY_WITH_ID);
        }
        if (!cityRepository.existsCityById(addressDto.getCityId())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_CITY_WITH_ID);
        }
        if (!districtRepository.existsDistrictByIdAndCityId(addressDto.getDistrictId(), addressDto.getCityId())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_DISTRICT_WITH_ID);
        }
        if (!neighborhoodRepository.existsNeighborhoodByIdAndDistrictId(addressDto.getNeighborhoodId(), addressDto.getDistrictId())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_NEIGHBORHOOD_WITH_ID);
        }
        if (!streetRepository.existsStreetByIdAndNeighborhoodId(addressDto.getStreetId(), addressDto.getNeighborhoodId())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_STREET_WITH_ID);
        }
        Address address = AddressMapper.INSTANCE.convertToAddress(addressDto);
        Address returnedAddress = addressRepository.save(address);
        AddressDto returnedAddressDto = AddressMapper.INSTANCE.convertToAddressDto(returnedAddress);
        return returnedAddressDto;
    }

    public void deleteAddress(Long id){
        Address address = addressRepository.findAddressById(id)
                .orElseThrow(() ->  new EntityNotFoundException(ErrorMessage.NOT_FOUND_ADDRESS_WITH_ID));
        addressRepository.delete(address);
    }

    public AddressResponseDto findAddressById(Long id){
        if (!addressRepository.existsById(id)){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_ADDRESS_WITH_ID);
        }
        AddressQueryDto addressQueryDto = addressRepository.findAddressByIdWithNames(id);
        return AddressMapper.INSTANCE.convertToAddressResponseDto(addressQueryDto);
    }

}
/* throw new EntityNotFoundException(String.format("There is not a country which name is %s! Please first save this country!", cityRequestDto.getCountryCode()));*/