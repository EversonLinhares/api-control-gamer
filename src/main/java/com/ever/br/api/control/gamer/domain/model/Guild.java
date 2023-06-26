package com.ever.br.api.control.gamer.domain.model;

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
@SQLDelete(sql = "update tb_guild set ativo = false where id = ? " )
@Where(clause = "ativo=true")
@Table(name = "tb_guild")
public class Guild implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer nivel;

    @OneToMany(mappedBy = "guild", fetch = FetchType.LAZY)
    private List<Player> players;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

}
