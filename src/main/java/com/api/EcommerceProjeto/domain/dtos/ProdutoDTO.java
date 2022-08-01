package com.api.EcommerceProjeto.domain.dtos;

import java.util.Date;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.api.EcommerceProjeto.domain.Loja;
import com.api.EcommerceProjeto.domain.Produto;
import com.api.EcommerceProjeto.domain.enums.Categoria;
import com.api.EcommerceProjeto.domain.enums.Promocao;
import com.api.EcommerceProjeto.domain.enums.Status;

public class ProdutoDTO extends Produto {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "O campo NAME não poderá ser nulo")
	protected String name;
	@NotNull(message = "O valor do produto não poderá ser nulo")
	protected Double produtoValor;

	protected Double produtoAntigoValor;
	@NotNull(message = "A descrição do produto não poderá ser nulo")
	protected String produtoDescricao;
	protected Categoria categoria;
	protected Integer produtoEstoque;
	protected Status status;

	protected Promocao promocaoStatus;
	protected String produtoImagem;
	protected Double produtoDesconto;
	protected LocalDate dataCriacao;
	protected Date dataLimitePromocao;

	protected Loja loja;
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Produto obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.produtoValor = obj.getProdutoValor();
		this.produtoAntigoValor = obj.getProdutoAntigoValor();
		this.produtoDescricao = obj.getProdutoDescricao();
		this.categoria = obj.getCategoria();
		this.produtoEstoque = obj.getProdutoEstoque();
		this.status = obj.getStatus();
		this.promocaoStatus = obj.getPromocaoStatus();
		this.produtoImagem = obj.getProdutoImagem();
		this.produtoDesconto = obj.getProdutoDesconto();
		this.dataLimitePromocao = obj.getDataLimitePromocao();
		this.dataCriacao = obj.getDataCriacao();
		this.loja = obj.getLoja();
	}
	public Loja getLoja() {
		return loja;
	}
	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getProdutoValor() {
		return produtoValor;
	}

	public void setProdutoValor(Double produtoValor) {
		this.produtoValor = produtoValor;
	}

	public Double getProdutoAntigoValor() {
		return produtoAntigoValor;
	}

	public void setProdutoAntigoValor(Double produtoAntigoValor) {
		this.produtoAntigoValor = produtoAntigoValor;
	}

	public String getProdutoDescricao() {
		return produtoDescricao;
	}

	public void setProdutoDescricao(String produtoDescricao) {
		this.produtoDescricao = produtoDescricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getProdutoEstoque() {
		return produtoEstoque;
	}

	public void setProdutoEstoque(Integer produtoEstoque) {
		this.produtoEstoque = produtoEstoque;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Promocao getPromocaoStatus() {
		return promocaoStatus;
	}

	public void setPromocaoStatus(Promocao promocaoStatus) {
		this.promocaoStatus = promocaoStatus;
	}

	public String getProdutoImagem() {
		return produtoImagem;
	}

	public void setProdutoImagem(String produtoImagem) {
		this.produtoImagem = produtoImagem;
	}

	public Double getProdutoDesconto() {
		return produtoDesconto;
	}

	public void setProdutoDesconto(Double produtoDesconto) {
		this.produtoDesconto = produtoDesconto;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataLimitePromocao() {
		return dataLimitePromocao;
	}

	public void setDataLimitePromocao(Date dataLimitePromocao) {
		this.dataLimitePromocao = dataLimitePromocao;
	}
}