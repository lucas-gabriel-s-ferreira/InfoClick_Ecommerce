package com.api.EcommerceProjeto.domain.enums;

public enum Ramo {
    
    HARDWARE(0), SOFTWARE(1), AMBOS(2);

    private Integer codigo;

    private Ramo(Integer codigo){
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static Ramo toEnum(Integer cod){

        if(cod == null){
            return null;
        }

        for(Ramo x : Ramo.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Ramo não existente");
    }

}
