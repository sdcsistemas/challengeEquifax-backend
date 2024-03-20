package com.equifax.challenge.services;

import com.equifax.challenge.dtos.CoordinatesDTO;

public interface CoordinatesService {

    CoordinatesDTO findByLatAndLng(String lat, String lng);

    CoordinatesDTO insert(CoordinatesDTO coordinatesDTO);
}
