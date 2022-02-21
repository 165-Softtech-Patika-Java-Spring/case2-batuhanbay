package com.softtechbootcamp.case2.dto.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityRequestDto {
    private String name;
    private String plateNumber;
    private String countryCode;
}
