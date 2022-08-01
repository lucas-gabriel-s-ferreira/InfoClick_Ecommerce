package com.api.EcommerceProjeto.domain.enums;

public enum Categoria {
    

    NADA(0),HARDWARE(1), SOFTWARE(2), AMBOS(3);

    private Integer codigo;

    private Categoria(Integer codigo){
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static Categoria toEnum(Integer cod){

        if(cod == null){
            return null;
        }

        for(Categoria x : Categoria.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Categoria não existente");
    }
}
