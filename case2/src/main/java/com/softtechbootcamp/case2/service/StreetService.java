package com.softtechbootcamp.case2.service;

import com.softtechbootcamp.case2.dto.street.StreetRequestDto;
import com.softtechbootcamp.case2.dto.street.StreetResponseDto;
import com.softtechbootcamp.case2.dto.street.StreetUpdateDto;
import com.softtechbootcamp.case2.enums.ErrorMessage;
import com.softtechbootcamp.case2.exceptions.DoesNotExistException;
import com.softtechbootcamp.case2.exceptions.DuplicateEntityException;
import com.softtechbootcamp.case2.exceptions.EntityNotFoundException;
import com.softtechbootcamp.case2.mapper.StreetMapper;
import com.softtechbootcamp.case2.model.Street;
import com.softtechbootcamp.case2.repository.NeighborhoodRepository;
import com.softtechbootcamp.case2.repository.StreetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StreetService {

    StreetRepository streetRepository;
    NeighborhoodRepository neighborhoodRepository;

    public StreetResponseDto saveStreet(StreetRequestDto streetRequestDto){
        if (checkIfPropertyProper(streetRequestDto.getName())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_NAME_PARAMETER);
        }
        if(streetRequestDto.getNeighborhoodId() != null && !neighborhoodRepository.existsById(streetRequestDto.getNeighborhoodId())){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_NEIGHBORHOOD_WITH_ID);
        }
        if (streetRepository.existsStreetByNameAndNeighborhoodId(streetRequestDto.getName(), streetRequestDto.getNeighborhoodId())){
            throw new DuplicateEntityException(ErrorMessage.HAS_DUPLICATE_NAME);
        }
        Street street = StreetMapper.INSTANCE.convertToStreet(streetRequestDto);
        Street returnedStreet = streetRepository.save(street);
        StreetResponseDto returnedStreetResponseDto = StreetMapper.INSTANCE.convertToStreetResponseDto(returnedStreet);
        return returnedStreetResponseDto;
    }

    public  StreetResponseDto updateStreet(StreetUpdateDto streetUpdateDto, Long neighborhoodId) {
        if (checkIfPropertyProper(streetUpdateDto.getName())){
            throw new DoesNotExistException(ErrorMessage.DOES_NOT_EXIST_NAME_PARAMETER);
        }
        if (!neighborhoodRepository.existsById(neighborhoodId)){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_NEIGHBORHOOD_WITH_ID);
        }
        if (streetRepository.existsStreetByNameAndNeighborhoodId(streetUpdateDto.getName(), neighborhoodId)){
            throw new DuplicateEntityException(ErrorMessage.HAS_DUPLICATE_NAME);
        }
        Street street = streetRepository.findStreetByIdAndNeighborhoodId(streetUpdateDto.getId(), neighborhoodId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.NOT_FOUND_CITY_WITH_ID));
        street.setName(streetUpdateDto.getName());
        Street returnedStreet = streetRepository.save(street);
        StreetResponseDto streetResponseDto = StreetMapper.INSTANCE.convertToStreetResponseDto(returnedStreet);
        return streetResponseDto;
    }

    public List<StreetResponseDto> findStreetsByNeighborhood(Long neighborhoodId){
        if (!neighborhoodRepository.existsById(neighborhoodId)){
            throw new EntityNotFoundException(ErrorMessage.NOT_FOUND_NEIGHBORHOOD_WITH_ID);
        }
        List<Street> streetList = streetRepository.findAllByNeighborhoodId(neighborhoodId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.NOT_FOUND_STREETS_WITH_ID));
        return  StreetMapper.INSTANCE.convertToStreetResponseDtoList(streetList);
    }

    private boolean checkIfPropertyProper(String property){
        return property.isEmpty() || property.isBlank();
    }

}
