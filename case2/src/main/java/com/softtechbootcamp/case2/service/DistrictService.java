package com.softtechbootcamp.case2.service;

import com.softtechbootcamp.case2.dto.district.DistrictRequestDto;
import com.softtechbootcamp.case2.dto.district.DistrictResponseDto;
import com.softtechbootcamp.case2.enums.ErrorMessage;
import com.softtechbootcamp.case2.exceptions.DoesNotExistException;
import com.softtechbootcamp.case2.exceptions.DuplicateEntityException;
import com.softtechbootcamp.case2.exceptions.EntityNotFoundException;
import com.softtechbootcamp.case2.mapper.DistrictMapper;
import com.softtechbootcamp.case2.model.District;
import com.softtechbootcamp.case2.repository.CityRepository;
import com.softtechbootcamp.case2.repository.DistrictRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DistrictService {

    DistrictRepository districtRepository;
    CityRepository cityRepository;

    public DistrictResponseDto saveDistrict(DistrictRequestDto districtRequestDto){
        if(districtRequestDto.getCityId() != null && !cityRepository.existsById(districtRequestDto.getCityId())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_CITY_WITH_ID);
        }
        if (checkIfPropertyProper(districtRequestDto.getName())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_NAME_PARAMETER);
        }
        if (districtRepository.existsDistrictByNameAndCityId(districtRequestDto.getName(), districtRequestDto.getCityId())){
            throw new DuplicateEntityException(ErrorMessage.HAS_DUPLICATE_NAME);
        }
        District district = DistrictMapper.INSTANCE.convertToDistrict(districtRequestDto);
        District returnedDistrict = districtRepository.save(district);
        DistrictResponseDto returnedDistrictResponseDto = DistrictMapper.INSTANCE.convertToDistrictResponseDto(returnedDistrict);
        return returnedDistrictResponseDto;
    }

    public List<DistrictResponseDto> findDistrictsByCity(Long cityId){
        if (!cityRepository.existsById(cityId)){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_CITY_WITH_ID);
        }
        List<District> districtList = districtRepository.findAllByCityId(cityId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.NOT_FOUND_DISTRICTS_WITH_ID));
        return DistrictMapper.INSTANCE.convertToDistrictResponseDto(districtList);
    }

    private boolean checkIfPropertyProper(String property){
        return property.isEmpty() || property.isBlank();
    }
}
