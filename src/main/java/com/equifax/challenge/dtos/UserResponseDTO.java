package com.equifax.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String message;
    private boolean result;
    private UsersDTO user;
    private TokenDTO data;

}
