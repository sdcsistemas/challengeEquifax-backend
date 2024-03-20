package com.equifax.challenge.persistences.repositories;

import com.equifax.challenge.persistences.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findFirstByEmail(String email);

    Optional<Users> findFirstByEmailAndPassword(String email, String password);
}
