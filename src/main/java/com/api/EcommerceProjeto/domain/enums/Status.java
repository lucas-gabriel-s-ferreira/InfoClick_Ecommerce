package com.api.EcommerceProjeto.domain.enums;

public enum Status {
	ATIVO(0), INATIVO(1);
	
	private Integer codigo;
	
	private Status(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status Inválido");
	}	
}