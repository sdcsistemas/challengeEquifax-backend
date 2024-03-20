package com.equifax.challenge.controllers;

import com.equifax.challenge.dtos.UsersDTO;
import com.equifax.challenge.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UsersController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsersDTO loginObj) {
        return ResponseEntity.ok(loginService.login(loginObj));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody UsersDTO usersDTO) {
        return ResponseEntity.ok(loginService.insert(usersDTO));
    }
}
