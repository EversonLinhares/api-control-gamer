package com.ever.br.api.control.gamer.domain.repository;

import com.ever.br.api.control.gamer.domain.model.entity.Cla;
import com.ever.br.api.control.gamer.domain.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    @Query(" select p from Player p where p.nick = ?1 ")
    Optional<Player> findByNick(String nick);

    @Query(" select p from Player p " +
              "where (:nick is null or lower(p.nick) like lower(concat('%', :nick, '%'))) ")
    List<Player> findAll(@Param("nick") String nick);

}