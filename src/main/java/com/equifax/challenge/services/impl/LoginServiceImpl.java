package com.equifax.challenge.services.impl;

import com.equifax.challenge.dtos.TokenDTO;
import com.equifax.challenge.dtos.UsersDTO;
import com.equifax.challenge.dtos.UserResponseDTO;
import com.equifax.challenge.persistences.models.Users;
import com.equifax.challenge.persistences.repositories.UserRepository;
import com.equifax.challenge.services.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    @Override
    public UserResponseDTO login(UsersDTO dto) {
        Optional<Users> userOpt = userRepository.findFirstByEmailAndPassword(dto.getEmail(), encrypt(dto.getPassword()));
        if (!userOpt.isPresent()) {
            log.info("User with Email {} not found in the database", dto.getEmail());
            return new UserResponseDTO(
                    "Login Erroneo. Email o contraseña incorrecta.", false, dto, null);
        }
        log.info("User with Email {} is found", dto.getEmail());
        return new UserResponseDTO(
                "Login success", true, dto, new TokenDTO(generateRandomToken()));
    }

    @Override
    public UsersDTO insert(UsersDTO dto) {
        Optional<Users> existUserOpt = userRepository.findFirstByEmail(dto.getEmail());
        if (existUserOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }
        Users user = userRepository.save(new Users(dto.getEmail(), encrypt(dto.getPassword())));
        log.info("User inserted in the database: {}", dto);
        return new UsersDTO(user.getEmail(), user.getPassword());
    }

    private String generateRandomToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    private String encrypt(String data) {
        String pass = "-";
        try {
            byte[] bytes = MessageDigest.getInstance("MD5").digest(data.getBytes());
            BigInteger no = new BigInteger(1, bytes);
            pass = no.toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
        } catch (Exception e) {
            log.error("Unexpected error encrypting data ", e);
        }
        return pass;
    }
}
