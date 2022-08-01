package com.api.EcommerceProjeto.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.api.EcommerceProjeto.domain.Empreendedor;
import com.api.EcommerceProjeto.domain.Loja;
import com.api.EcommerceProjeto.domain.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LojaDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer idLoja;
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	protected String corDeFundo;
	@NotNull(message = "O campo NOME DA LOJA não poderá ser nulo")
	protected String nomeLoja;
	protected String descricaoLoja;
	@NotNull(message = "Um empreendedor precisa estar vinculado a Loja")
	protected Empreendedor empreendedor;
	protected List<Produto> produtos;
	protected String imagemLoja;
    public LojaDTO() {
		super();
	}

	public LojaDTO(Loja obj) {
		super();
		this.idLoja = obj.getIdLoja();
		this.corDeFundo = obj.getCorDeFundo();
		this.nomeLoja = obj.getNomeLoja();
		this.descricaoLoja = obj.getDescricaoLoja();
		this.produtos = obj.getProdutos();
		this.imagemLoja = obj.getImagemLoja();
	}

	public String getImagemLoja() {
		return imagemLoja;
	}

	public void  setImagemLoja(String imagemLoja) {
			this.imagemLoja = imagemLoja;
	}

	public Integer getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Integer idLoja) {
		this.idLoja = idLoja;
	}

	public Empreendedor getEmpreendedor() {
		return empreendedor;
	}

	public void setEmpreendedor(Empreendedor empreendedor) {
		this.empreendedor = empreendedor;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCorDeFundo() {
		return corDeFundo;
	}

	public void setCorDeFundo(String corDeFundo) {
		this.corDeFundo = corDeFundo;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public String getDescricaoLoja() {
		return descricaoLoja;
	}

	public void setDescricaoLoja(String descricaoLoja) {
		this.descricaoLoja = descricaoLoja;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

}
