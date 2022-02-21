package com.softtechbootcamp.case2.dto.street;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StreetRequestDto {
    String name;
    private Long neighborhoodId;
}
