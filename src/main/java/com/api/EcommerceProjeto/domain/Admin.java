package com.api.EcommerceProjeto.domain;

import com.api.EcommerceProjeto.domain.dtos.AdminDTO;
import com.api.EcommerceProjeto.domain.enums.Perfil;

import javax.persistence.Entity;
import java.util.stream.Collectors;

@Entity
public class Admin extends Pessoa{
    private static final long serialVersionUID = 1L;

    public Admin(){
        super();
        addPerfil(Perfil.ADMIN);
    }

    public Admin(AdminDTO obj){
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
        this.perfil = obj.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());

    }

    public Admin(Integer id, String nome, String email, String password, String celular, String cep, String estado, String cidade, String bairro, String rua, String numero) {
        super(id, nome, email, password, celular, cep, estado, cidade, bairro, rua, numero);
        addPerfil(Perfil.ADMIN);
    }
}
