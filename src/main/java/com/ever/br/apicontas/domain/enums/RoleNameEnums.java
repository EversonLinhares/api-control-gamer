package com.ever.br.apicontas.domain.enums;

import lombok.Getter;

@Getter
public enum RoleNameEnums {

    ROLE_ADMIN(1),ROLE_USER(2);
    private Integer codigo;
    private RoleNameEnums(Integer codigo) {this.codigo = codigo;}

    public static RoleNameEnums obterRolePorCodigo(Integer codigo) {
        for (RoleNameEnums role : values()) {
            if (role.getCodigo().equals(codigo)) {
                return role;
            }
        }
        return null;
    }

    public static Long obterRolePorNome(String nomeRole) {
        if (nomeRole.equalsIgnoreCase("ROLE_ADMIN")) {
            return 1L;
        } else if (nomeRole.equalsIgnoreCase("ROLE_USER")) {
            return 2L;
        }
        return null;
    }
}
