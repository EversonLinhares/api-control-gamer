package com.ever.br.apicontas.repository;

import com.ever.br.apicontas.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.cpf = ?1")
    User findByCpf(String cpf);

    Optional<User> findByUsername (String username);
}
