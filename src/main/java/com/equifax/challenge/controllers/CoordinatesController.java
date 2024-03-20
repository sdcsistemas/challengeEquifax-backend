package com.equifax.challenge.controllers;

import com.equifax.challenge.dtos.CoordinatesDTO;
import com.equifax.challenge.services.CoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/coordinates")
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    @GetMapping("/findByLatAndLng/{lat}/{lng}")
    public ResponseEntity<CoordinatesDTO> findByLatAndLng(@PathVariable("lat") String lat, @PathVariable("lng") String lng) {
        return ResponseEntity.ok(coordinatesService.findByLatAndLng(lat, lng));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody CoordinatesDTO coordinatesDTO) {
        return ResponseEntity.ok(coordinatesService.insert(coordinatesDTO));
    }
}
