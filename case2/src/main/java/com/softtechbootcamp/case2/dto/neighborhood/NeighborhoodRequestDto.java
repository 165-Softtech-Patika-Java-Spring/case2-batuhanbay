package com.softtechbootcamp.case2.dto.neighborhood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NeighborhoodRequestDto {
    private String name;
    private Long districtId;
}
