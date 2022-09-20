package com.ever.br.api.control.gamer.domain.repository;

import com.ever.br.api.control.gamer.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername (String username);
}
