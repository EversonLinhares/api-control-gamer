package com.ever.br.api.control.gamer.domain.model.entity;

import com.ever.br.api.control.gamer.domain.enums.RoleNameEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleNameEnums roleName;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
