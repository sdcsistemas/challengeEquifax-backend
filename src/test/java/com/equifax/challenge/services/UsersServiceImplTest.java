package com.equifax.challenge.services;

import com.equifax.challenge.Datos;
import com.equifax.challenge.dtos.UserResponseDTO;
import com.equifax.challenge.dtos.UsersDTO;
import com.equifax.challenge.persistences.models.Users;
import com.equifax.challenge.persistences.repositories.UserRepository;
import com.equifax.challenge.services.impl.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    LoginServiceImpl service;

    @Captor
    ArgumentCaptor<Long> captor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testInsertUser() {
        UserResponseDTO newUser = Datos.createUser1();

        when(repository.save(any(Users.class))).then(new Answer<Users>() {
            Long secuencia = 8L;

            @Override
            public Users answer(InvocationOnMock invocation) throws Throwable {
                Users user = invocation.getArgument(0);
                user.setId(secuencia++);
                return user;
            }
        });

        UsersDTO userDTO = service.insert(newUser.getUser());

        assertNotNull(userDTO);
        assertEquals("sergio.crocetta@gmail.com", userDTO.getEmail());

        verify(repository).save(any(Users.class));
    }
}
