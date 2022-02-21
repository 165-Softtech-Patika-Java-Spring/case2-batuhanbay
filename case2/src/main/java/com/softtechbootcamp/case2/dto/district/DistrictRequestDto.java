package com.softtechbootcamp.case2.dto.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DistrictRequestDto {
    private String name;
    private Long cityId;
}
