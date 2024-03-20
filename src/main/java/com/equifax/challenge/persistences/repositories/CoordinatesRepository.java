package com.equifax.challenge.persistences.repositories;

import com.equifax.challenge.persistences.models.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
    Optional<Coordinates> findByLatAndLng(String lat, String lng);
}
