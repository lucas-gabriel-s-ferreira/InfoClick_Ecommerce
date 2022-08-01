package com.api.EcommerceProjeto.domain.dtos;
import com.api.EcommerceProjeto.domain.Empreendedor;
import com.api.EcommerceProjeto.domain.Loja;
import com.api.EcommerceProjeto.domain.Pessoa;
import com.api.EcommerceProjeto.domain.enums.Perfil;
import com.api.EcommerceProjeto.domain.enums.Ramo;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EmpreendedorDTO extends Pessoa {

    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull(message = "O campo Nome não poderá ser nulo")
    protected String nome;

    @CNPJ(message = "CNPJ Inválido")
    @NotNull(message = "O CNPJ não poderá ser nulo")
    protected String cnpj;

    @Email(message = "Email inválido")
    @NotNull(message = "O E-MAIL não poderá ser nulo")
    protected String email;

    @NotNull(message = "O campo de SENHA não pode ser nulo")
    protected String password;

    protected Set<Integer> perfil = new HashSet<>();;
    protected String celular;
    @NotNull(message = "O campo de RAMO não pode ser nulo, escolha alguma das opções")
    protected Ramo ramo;
    protected String cep;
    protected String estado;
    protected String cidade;
    protected String bairro;
    protected String rua;
    protected String numero;
    protected Loja loja;

    public EmpreendedorDTO() {
        super();
        addPerfil(Perfil.EMPREENDEDOR);
    }

    public EmpreendedorDTO(Empreendedor obj) {
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
        addPerfil(Perfil.EMPREENDEDOR);
    }

    public Set<Perfil> getPerfil() {
        return perfil.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfil.add(perfil.getCodigo());
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
      this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Ramo getRamo() {
        return ramo;
    }

    public void setRamo(Ramo ramo) {
        this.ramo = ramo;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
