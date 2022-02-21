package com.softtechbootcamp.case2.service;

import com.softtechbootcamp.case2.dto.city.CityRequestDto;
import com.softtechbootcamp.case2.dto.city.CityResponseDto;
import com.softtechbootcamp.case2.enums.ErrorMessage;
import com.softtechbootcamp.case2.exceptions.DoesNotExistException;
import com.softtechbootcamp.case2.exceptions.DuplicateEntityException;
import com.softtechbootcamp.case2.exceptions.EntityNotFoundException;
import com.softtechbootcamp.case2.mapper.CityMapper;
import com.softtechbootcamp.case2.model.City;
import com.softtechbootcamp.case2.repository.CityRepository;
import com.softtechbootcamp.case2.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CityService {

    CityRepository cityRepository;
    CountryRepository countryRepository;

    public CityResponseDto saveCity(CityRequestDto cityRequestDto){
        if (checkIfPropertyProper(cityRequestDto.getName())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_NAME_PARAMETER);
        }
        if (checkIfPropertyProper(cityRequestDto.getPlateNumber())) {
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_PLATE_NUMBER_PARAMETER);
        }
        if (checkIfPropertyProper(cityRequestDto.getCountryCode())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_COUNTRY_CODE_PARAMETER);
        }
        if(!countryRepository.existsCountryByCode(cityRequestDto.getCountryCode())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_COUNTRY_WITH_CODE);
        }
        if (cityRepository.existsCityByPlateNumberAndCountryCode(cityRequestDto.getPlateNumber(), cityRequestDto.getCountryCode())){
            throw new DuplicateEntityException(ErrorMessage.HAS_DUPLICATE_PLATE_NUMBER);
        }
        if (cityRepository.existsCityByNameAndCountryCode(cityRequestDto.getName(), cityRequestDto.getCountryCode())){
            throw new DuplicateEntityException(ErrorMessage.HAS_DUPLICATE_NAME);
        }
        City city = CityMapper.INSTANCE.convertToCity(cityRequestDto);
        City returnedCity = cityRepository.save(city);
        CityResponseDto returnedCityResponseDto = CityMapper.INSTANCE.convertToCityResponseDto(returnedCity);
        return returnedCityResponseDto;
    }

    public CityResponseDto findByPlateNumber(String countryCode, String plateNumber){
        if (checkIfPropertyProper(plateNumber)){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_PLATE_NUMBER_PARAMETER);
        }
        if (checkIfPropertyProper(countryCode)){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_COUNTRY_CODE_PARAMETER);
        }
        City city = cityRepository.findCityByPlateNumberAndCountryCode(plateNumber, countryCode)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.NOT_FOUND_CITY_WITH_PLATE_NUMBER));
        CityResponseDto returnedCityResponseDto = CityMapper.INSTANCE.convertToCityResponseDto(city);
        return returnedCityResponseDto;
    }

    private boolean checkIfPropertyProper(String property){
        return property.isEmpty() || property.isBlank();
    }
}
