package com.softtechbootcamp.case2.service;

import com.softtechbootcamp.case2.dto.country.CountryDto;
import com.softtechbootcamp.case2.enums.ErrorMessage;
import com.softtechbootcamp.case2.exceptions.DoesNotExistException;
import com.softtechbootcamp.case2.exceptions.DuplicateEntityException;
import com.softtechbootcamp.case2.exceptions.EntityNotFoundException;
import com.softtechbootcamp.case2.mapper.CountryMapper;
import com.softtechbootcamp.case2.model.Country;
import com.softtechbootcamp.case2.repository.CityRepository;
import com.softtechbootcamp.case2.repository.CountryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {
    @NonNull
    CountryRepository countryRepository;
    @NonNull
    CityRepository cityRepository;

    public CountryDto saveCountry(CountryDto countryDto){
        if (countryRepository.existsCountryByCode(countryDto.getCode())){
            throw new DuplicateEntityException(ErrorMessage.HAS_DUPLICATE_COUNTRY_CODE);
        }
        if (checkIfPropertyProper(countryDto.getCode())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_COUNTRY_CODE_PARAMETER);
        }
        if (checkIfPropertyProper(countryDto.getName())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_NAME_PARAMETER);
        }
        Country country = CountryMapper.INSTANCE.convertToCountry(countryDto);
        Country returnedCountry = countryRepository.save(country);
        CountryDto returnedCountryDto = CountryMapper.INSTANCE.convertToCountryDto(returnedCountry);
        return returnedCountryDto;
    }

    public CountryDto findByCountryCode(String countryCode){
        if (checkIfPropertyProper(countryCode)){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_COUNTRY_CODE_PARAMETER);
        }
        Country country = countryRepository.findCountryByCode(countryCode)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.NOT_FOUND_COUNTRY_WITH_CODE));
        CountryDto countryDto = CountryMapper.INSTANCE.convertToCountryDto(country);
        return countryDto;
    }

    private boolean checkIfPropertyProper(String property){
        return property.isEmpty() || property.isBlank();
    }
}
