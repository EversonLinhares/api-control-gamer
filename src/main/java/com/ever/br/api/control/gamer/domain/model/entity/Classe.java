package com.ever.br.api.control.gamer.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "update tb_classe set ativo = false where id = ? " )
@Where(clause = "ativo=true")
@Table(name = "tb_classe")
public class Classe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    private List<Player> players;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

}

