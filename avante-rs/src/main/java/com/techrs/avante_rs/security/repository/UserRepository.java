package com.techrs.avante_rs.security.repository;


import com.techrs.avante_rs.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
