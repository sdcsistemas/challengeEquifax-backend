package com.equifax.challenge.services;

import com.equifax.challenge.dtos.UsersDTO;
import com.equifax.challenge.dtos.UserResponseDTO;

public interface LoginService {

    UserResponseDTO login(UsersDTO usersDTO);

    UsersDTO insert(UsersDTO usersDTO);
}
