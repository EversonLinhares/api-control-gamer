package com.ever.br.api.control.gamer.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer level;

    @NotNull
    @Column(nullable = false,scale = 3)
    private BigDecimal power;

    @NotNull
    @Column(nullable = false)
    private Integer qtdCodex;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cla cla;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_user")
    private User user;
}
