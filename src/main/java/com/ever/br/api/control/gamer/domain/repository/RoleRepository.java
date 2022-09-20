package com.ever.br.api.control.gamer.domain.repository;

import com.ever.br.api.control.gamer.domain.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findById(Role role);

}
