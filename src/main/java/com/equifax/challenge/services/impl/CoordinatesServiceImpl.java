package com.equifax.challenge.services.impl;

import com.equifax.challenge.dtos.CoordinatesDTO;
import com.equifax.challenge.persistences.models.Coordinates;
import com.equifax.challenge.persistences.repositories.CoordinatesRepository;
import com.equifax.challenge.services.CoordinatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CoordinatesServiceImpl implements CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;
    private final ModelMapper modelMapper;

    @Override
    public CoordinatesDTO findByLatAndLng(String lat, String lng) {
        Optional<Coordinates> coordinateOpt = coordinatesRepository.findByLatAndLng(lat, lng);
        if (!coordinateOpt.isPresent()) {
            log.info("Coordinates with lat {} and long {} not found in the database", lat, lng);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        log.info("Coordinates with lat {} and long {} is", lat, lng);
        return this.convertToDto(coordinateOpt.get());
    }

    @Override
    public CoordinatesDTO insert(CoordinatesDTO coordinatesDTO) {
        Optional<Coordinates> existCoordinateOpt = coordinatesRepository.findById(coordinatesDTO.getId());
        if (existCoordinateOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }
        Coordinates coordinates = coordinatesRepository.save(this.convertToEntity(coordinatesDTO));
        log.info("Coordinates inserted in the database: {}", coordinatesDTO);
        return this.convertToDto(coordinates);
    }

    private CoordinatesDTO convertToDto(Coordinates entity) {
        return modelMapper.map(entity, CoordinatesDTO.class);
    }

    private Coordinates convertToEntity(CoordinatesDTO dto) {
        return modelMapper.map(dto, Coordinates.class);
    }
}
