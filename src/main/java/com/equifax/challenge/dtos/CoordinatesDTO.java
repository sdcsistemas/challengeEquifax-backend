package com.equifax.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDTO {

    private Long id;
    private String lat;
    private String lng;
    private String description;
    private String observation;

}
