package com.api.EcommerceProjeto.domain;

import com.api.EcommerceProjeto.domain.dtos.EmpreendedorDTO;

import com.api.EcommerceProjeto.domain.enums.Perfil;
import com.api.EcommerceProjeto.domain.enums.Ramo;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Entity
public class Empreendedor extends Pessoa{

    private static final long serialVersionUID = 1L;

    @CNPJ
    @Column(unique = true)
    private String cnpj;
    private Ramo ramo;
	@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    @OneToOne(mappedBy = "empreendedor")
    private Loja loja;

    public Empreendedor() {
        super();
        addPerfil(Perfil.EMPREENDEDOR);
    }

    public Empreendedor(EmpreendedorDTO obj){
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cnpj = obj.getCnpj();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.perfil = obj.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.celular = obj.getCelular();
        this.ramo = obj.getRamo();
        this.cep = obj.getCep();
        this.estado = obj.getEstado();
        this.cidade = obj.getCidade();
        this.bairro = obj.getBairro();
        this.rua = obj.getRua();
        this.numero = obj.getNumero();
        this.loja = obj.getLoja();
    }

 

	public Empreendedor(Integer id, String nome, @CNPJ String cnpj, String email, String password,
            String celular, Ramo ramo, String cep, String estado, String cidade, String bairro, String rua,
            String numero) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.celular = celular;
        this.ramo = ramo;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        addPerfil(Perfil.EMPREENDEDOR);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Ramo getRamo() {
        return ramo;
    }

    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }
    public LocalDate getDataCriacao() {
 		return dataCriacao;
 	}

 	public void setDataCriacao(LocalDate dataCriacao) {
 		this.dataCriacao = dataCriacao;
 	}
    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
