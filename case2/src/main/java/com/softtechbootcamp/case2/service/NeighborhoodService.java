package com.softtechbootcamp.case2.service;

import com.softtechbootcamp.case2.dto.neighborhood.NeighborhoodRequestDto;
import com.softtechbootcamp.case2.dto.neighborhood.NeighborhoodResponseDto;
import com.softtechbootcamp.case2.dto.neighborhood.NeighborhoodUpdateDto;
import com.softtechbootcamp.case2.enums.ErrorMessage;
import com.softtechbootcamp.case2.exceptions.DoesNotExistException;
import com.softtechbootcamp.case2.exceptions.DuplicateEntityException;
import com.softtechbootcamp.case2.exceptions.EntityNotFoundException;
import com.softtechbootcamp.case2.mapper.NeighborhoodMapper;
import com.softtechbootcamp.case2.model.Neighborhood;
import com.softtechbootcamp.case2.repository.DistrictRepository;
import com.softtechbootcamp.case2.repository.NeighborhoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NeighborhoodService {
    NeighborhoodRepository neighborhoodRepository;
    DistrictRepository districtRepository;

    public NeighborhoodResponseDto saveNeighborhood(NeighborhoodRequestDto neighborhoodRequestDto){
        if (checkIfPropertyProper(neighborhoodRequestDto.getName())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_NAME_PARAMETER);
        }
        if(neighborhoodRequestDto.getDistrictId() != null && !districtRepository.existsById(neighborhoodRequestDto.getDistrictId())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_DISTRICT_WITH_ID);
        }
        if (neighborhoodRepository.existsNeighborhoodByNameAndDistrictId(neighborhoodRequestDto.getName(), neighborhoodRequestDto.getDistrictId())){
            throw new DuplicateEntityException(ErrorMessage.HAS_DUPLICATE_NAME);
        }
        Neighborhood neighborhood = NeighborhoodMapper.INSTANCE.convertToNeighborhood(neighborhoodRequestDto);
        Neighborhood returnedNeighborhood = neighborhoodRepository.save(neighborhood);
        NeighborhoodResponseDto returnedNeighborhoodResponseDto = NeighborhoodMapper.INSTANCE.convertToNeighborhoodResponseDto(returnedNeighborhood);
        return returnedNeighborhoodResponseDto;
    }

    public  NeighborhoodResponseDto updateNeighborhood(NeighborhoodUpdateDto neighborhoodUpdateDto, Long districtId) {
        if (checkIfPropertyProper(neighborhoodUpdateDto.getName())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_NAME_PARAMETER);
        }
        if (!districtRepository.existsById(districtId)){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_DISTRICT_WITH_ID);
        }
        if (neighborhoodRepository.existsNeighborhoodByNameAndDistrictId(neighborhoodUpdateDto.getName(), districtId)){
            throw new DuplicateEntityException(ErrorMessage.HAS_DUPLICATE_NAME);
        }
        Neighborhood neighborhood = neighborhoodRepository.findNeighborhoodByIdAndDistrictId(neighborhoodUpdateDto.getId(), districtId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.NOT_FOUND_NEIGHBORHOOD_WITH_ID));
        neighborhood.setName(neighborhoodUpdateDto.getName());
        Neighborhood returnedNeighborhood = neighborhoodRepository.save(neighborhood);
        NeighborhoodResponseDto neighborhoodResponseDto = NeighborhoodMapper.INSTANCE.convertToNeighborhoodResponseDto(returnedNeighborhood);
        return neighborhoodResponseDto;
    }

    public List<NeighborhoodResponseDto> findNeighborhoodsByDistrict(Long districtId){
        if (!districtRepository.existsById(districtId)){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_DISTRICT_WITH_ID);
        }
        List<Neighborhood> neighborhoodList = neighborhoodRepository.findAllByDistrictId(districtId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.NOT_FOUND_NEIGHBORHOODS_WITH_ID));
        return  NeighborhoodMapper.INSTANCE.convertToNeighborhoodResponseDtoList(neighborhoodList);
    }

    private boolean checkIfPropertyProper(String property){
        return property.isEmpty() || property.isBlank();
    }

}
