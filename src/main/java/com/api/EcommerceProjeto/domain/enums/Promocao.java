package com.api.EcommerceProjeto.domain.enums;

public enum Promocao {

    ATIVADA(0), DESATIVADA(1);

    private Integer codigo;

    private Promocao(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static Promocao toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for(Promocao x : Promocao.values()) {
            if(cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Status Inválido");
    }
}
