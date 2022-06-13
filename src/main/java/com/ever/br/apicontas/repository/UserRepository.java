package com.ever.br.apicontas.repository;

import com.ever.br.apicontas.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
