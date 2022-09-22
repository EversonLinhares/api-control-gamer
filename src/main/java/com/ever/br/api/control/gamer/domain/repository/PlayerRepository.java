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

//    exemplo query
//@Query(" select s from Setor s join s.unidade u join s.hierarquia h left join CentroCusto cc on cc.setor.id = s.id " +
//        "where (:descricao is null or lower(s.descricao) like lower(concat('%', :descricao, '%'))) " +
//        "and (:sigla is null or lower(s.sigla) = lower( :sigla )) " +
//        "and (:status is null or (s.status) = ( :status )) " +
//        "and (:unidadeId is null or (s.unidade.id) = (:unidadeId)) " +
//        "and (:hierarquiaId is null or (s.hierarquia.id) = (:hierarquiaId)) " +
//        "and (:setorPaiId is null or (s.setor.id) = (:setorPaiId)) " +
//        "and (:permiteEstagio is null or (s.permiteEstagio) = (:permiteEstagio)) " +
//        "and (:nomeCentroCusto is null or lower(cc.nome) = lower( :nomeCentroCusto )) " +
//        "and (:abonoEmergencia is null or (cc.abonoEmergencia) = ( :abonoEmergencia )) " )
//List<Setor> setorPesquisar(@Param("descricao") String descricao,
//                           @Param("setorPaiId") Long setorPaiId,
//                           @Param("unidadeId") Long unidadeId,
//                           @Param("hierarquiaId") Long hierarquiaId,
//                           @Param("sigla") String sigla,
//                           @Param("status") StatusEnum status,
//                           @Param("permiteEstagio") EstagioEnum permiteEstagio,
//                           @Param("nomeCentroCusto") String nomeCentroCusto,
//                           @Param("abonoEmergencia") AbonoEmergenciaEnum abonoEmergencia);
}
