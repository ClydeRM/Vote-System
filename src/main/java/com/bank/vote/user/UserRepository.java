package com.bank.vote.user;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

//      Optional<User> findByEmail(String email);
    @Procedure(name = "finduserbyemail")
    Optional<User> findUserByEmail(@Param("email_in") String email);
}
