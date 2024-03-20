package com.equifax.challenge;

import com.equifax.challenge.dtos.CoordinatesDTO;
import com.equifax.challenge.dtos.TokenDTO;
import com.equifax.challenge.dtos.UserResponseDTO;
import com.equifax.challenge.dtos.UsersDTO;
import com.equifax.challenge.persistences.models.Coordinates;
import com.equifax.challenge.persistences.models.Users;

import java.util.Optional;

public class Datos {

    public static UserResponseDTO createUser1() {
        return new UserResponseDTO(
                "Login success", true,
                new UsersDTO("sergio.crocetta@gmail.com", "112233"),
                new TokenDTO("Bearer sdasdasdasdasdasda"));
    }

    public static CoordinatesDTO createCoordinates() {
        return new CoordinatesDTO(3L, "777", "333",
                "Latidud Descentende al norte", "Longitud Descentende al sur");
    }

    public static Optional<Users> getUser1() {
        return Optional.of(new Users("sergio.crocetta@gmail.com", "112233"));
    }

    public static Optional<Coordinates> getCoordinates() {
        return Optional.of(new Coordinates(3L, "777", "333",
                "Latidud Descentende al norte", "Longitud Descentende al sur"));
    }
}
