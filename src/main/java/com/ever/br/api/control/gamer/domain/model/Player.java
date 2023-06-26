package com.ever.br.api.control.gamer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update tb_player set ativo = false where id = ? " )
@Builder
@Entity
@Table(name = "tb_player")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
    private String nick;

    @NotNull
    @Column(nullable = false)
    private Integer nivel;

    @NotNull
    @Column(nullable = false,scale = 3)
    private BigDecimal power;

    @NotNull
    @Column(nullable = false)
    private Integer qtdCodex;

    @NotNull
    @Column(nullable = false)
    private Boolean alt;

    @NotNull
    @Column(nullable = false)
    private Boolean principal;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne(fetch = FetchType.EAGER)
    private Guild guild;

    @ManyToOne(fetch = FetchType.EAGER)
    private Classe classe;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_user")
    private User user;
}
