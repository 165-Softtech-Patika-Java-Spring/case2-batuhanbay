package com.softtechbootcamp.case2.controller;

import com.softtechbootcamp.case2.dto.address.AddressDto;
import com.softtechbootcamp.case2.dto.address.AddressResponseDto;
import com.softtechbootcamp.case2.dto.city.CityRequestDto;
import com.softtechbootcamp.case2.dto.city.CityResponseDto;
import com.softtechbootcamp.case2.dto.country.CountryDto;
import com.softtechbootcamp.case2.dto.district.DistrictRequestDto;
import com.softtechbootcamp.case2.dto.district.DistrictResponseDto;
import com.softtechbootcamp.case2.dto.neighborhood.NeighborhoodRequestDto;
import com.softtechbootcamp.case2.dto.neighborhood.NeighborhoodResponseDto;
import com.softtechbootcamp.case2.dto.neighborhood.NeighborhoodUpdateDto;
import com.softtechbootcamp.case2.dto.street.StreetRequestDto;
import com.softtechbootcamp.case2.dto.street.StreetResponseDto;
import com.softtechbootcamp.case2.dto.street.StreetUpdateDto;
import com.softtechbootcamp.case2.response.GeneralResponse;
import com.softtechbootcamp.case2.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/adresses")
@AllArgsConstructor
public class AdressController {

    CountryService countryService;
    CityService cityService;
    DistrictService districtService;
    NeighborhoodService neighborhoodService;
    StreetService streetService;
    AddressService addressService;

    @GetMapping("/countries/{countryCode}")
    public ResponseEntity findCountryByCountryCode(@PathVariable("countryCode") String countryCode){
        CountryDto countryDto = countryService.findByCountryCode(countryCode);
        return  ResponseEntity.ok(GeneralResponse.of(countryDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @GetMapping("{countryCode}/cities/{plateNumber}")
    public  ResponseEntity findCityByCityPlateNumber(@PathVariable("countryCode") String countryCode, @PathVariable("plateNumber") String plateNumber){
        CityResponseDto returnedCityResponseDto = cityService.findByPlateNumber(countryCode, plateNumber);
        return ResponseEntity.ok(GeneralResponse.of(returnedCityResponseDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @GetMapping("{cityId}/districts")
    public ResponseEntity findDistrictsByCity(@PathVariable("cityId") Long cityId){
        List<DistrictResponseDto> districtResponseDtoList = districtService.findDistrictsByCity(cityId);
        return ResponseEntity.ok(GeneralResponse.of(districtResponseDtoList, HttpStatus.OK, HttpStatus.OK.value()));
    }

    @GetMapping("{districtId}/neighborhoods")
    public  ResponseEntity findNeighborhoodsByDistrict(@PathVariable("districtId") Long districtId){
        List<NeighborhoodResponseDto> neighborhoodResponseDtoList = neighborhoodService.findNeighborhoodsByDistrict(districtId);
        return ResponseEntity.ok(GeneralResponse.of(neighborhoodResponseDtoList,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @GetMapping("{neighborhoodId}/streets")
    public  ResponseEntity findStreetsByNeighborhood(@PathVariable("neighborhoodId") Long neighborhoodId) {
        List<StreetResponseDto> streetResponseDtoList = streetService.findStreetsByNeighborhood(neighborhoodId);
        return ResponseEntity.ok(GeneralResponse.of(streetResponseDtoList,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity findAddressById(@PathVariable("id") Long id){
        AddressResponseDto addressResponseDto = addressService.findAddressById(id);
        return ResponseEntity.ok(GeneralResponse.of(addressResponseDto,HttpStatus.OK, HttpStatus.OK.value()));
    }


    @PostMapping("/countries")
    public ResponseEntity saveCountry(@RequestBody CountryDto countryDto) {
        CountryDto returnedCountryDto = countryService.saveCountry(countryDto);
        return ResponseEntity.ok(GeneralResponse.of(returnedCountryDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @PostMapping("/cities")
    public ResponseEntity saveCity(@RequestBody CityRequestDto cityRequestDtoDto){
        CityResponseDto returnedCityResponseDto = cityService.saveCity(cityRequestDtoDto);
        return ResponseEntity.ok(GeneralResponse.of(returnedCityResponseDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @PostMapping("/districts")
    public ResponseEntity saveDistrict(@RequestBody DistrictRequestDto districtRequestDto){
        DistrictResponseDto returnedDistrictResponseDto = districtService.saveDistrict(districtRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(returnedDistrictResponseDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @PostMapping("/neighborhoods")
    public ResponseEntity saveNeighborhood(@RequestBody NeighborhoodRequestDto neighborhoodRequestDto){
        NeighborhoodResponseDto returnedNeighborhoodResponseDto = neighborhoodService.saveNeighborhood(neighborhoodRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(returnedNeighborhoodResponseDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @PostMapping("/streets")
    public ResponseEntity saveStreet(@RequestBody StreetRequestDto streetRequestDto){
        StreetResponseDto returnedStreetResponseDto = streetService.saveStreet(streetRequestDto);
        return ResponseEntity.ok(GeneralResponse.of(returnedStreetResponseDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @PostMapping
   public  ResponseEntity saveAddress(@RequestBody AddressDto addressDto){
        AddressDto returnedAddressDto = addressService.saveAddress(addressDto);
        return ResponseEntity.ok(GeneralResponse.of(returnedAddressDto,HttpStatus.OK, HttpStatus.OK.value()));
    }


    @PutMapping("{districtId}/neighborhoods")
    public ResponseEntity updateNeighborhood(@RequestBody NeighborhoodUpdateDto neighborhoodUpdateDto, @PathVariable("districtId") Long districtId){
        NeighborhoodResponseDto returnedNeighborhoodResponseDto = neighborhoodService.updateNeighborhood(neighborhoodUpdateDto, districtId);
        return ResponseEntity.ok(GeneralResponse.of(returnedNeighborhoodResponseDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @PutMapping("{neighborhoodId}/streets")
    public ResponseEntity updateStreet(@RequestBody StreetUpdateDto streetUpdateDto, @PathVariable("neighborhoodId") Long neighborhoodId){
        StreetResponseDto returnedStreetResponseDto = streetService.updateStreet(streetUpdateDto, neighborhoodId);
        return ResponseEntity.ok(GeneralResponse.of(returnedStreetResponseDto,HttpStatus.OK, HttpStatus.OK.value()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok(GeneralResponse.empty(HttpStatus.OK, HttpStatus.OK.value()));
    }
}
