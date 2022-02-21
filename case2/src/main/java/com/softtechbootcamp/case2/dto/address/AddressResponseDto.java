package com.softtechbootcamp.case2.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressResponseDto {
    private String countryName;
    private String cityName;
    private String districtName;
    private String neighborhoodName;
    private String streetName;
    private String doorNo;
    private String buildingNo;
}
