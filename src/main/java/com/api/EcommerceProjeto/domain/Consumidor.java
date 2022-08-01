package com.api.EcommerceProjeto.domain;

import javax.persistence.*;

import com.api.EcommerceProjeto.domain.dtos.ConsumidorDTO;
import com.api.EcommerceProjeto.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Entity
public class Consumidor extends Pessoa {

	private static final long serialVersionUID = 1L;

	@CPF
	@Column(unique = true)
	private String cpf;
	@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

	public Consumidor() {
		super();
		addPerfil(Perfil.CONSUMIDOR);
	}

	public Consumidor(Integer id, String nome, String email, String password, String celular, String cep, String estado, String cidade, String bairro, String rua, String numero, String cpf) {
		super(id, nome, email, password, celular, cep, estado, cidade, bairro, rua, numero);
		this.cpf = cpf;
		addPerfil(Perfil.CONSUMIDOR);
	}

	public Consumidor(ConsumidorDTO obj){
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.celular = obj.getCelular();
		this.cep = obj.getCep();
		this.estado = obj.getEstado();
		this.cidade = obj.getCidade();
		this.bairro = obj.getBairro();
		this.rua = obj.getRua();
		this.numero = obj.getNumero();
		this.cpf = obj.getCpf();
		this.perfil = obj.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
