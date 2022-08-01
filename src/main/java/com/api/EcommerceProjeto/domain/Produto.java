package com.api.EcommerceProjeto.domain;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.api.EcommerceProjeto.domain.enums.Promocao;
import org.hibernate.annotations.CreationTimestamp;

import com.api.EcommerceProjeto.domain.dtos.ProdutoDTO;
import com.api.EcommerceProjeto.domain.enums.Categoria;
import com.api.EcommerceProjeto.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "produtoValor")
	private Double produtoValor;

	@Column(name = "produtoAntigoValor")
	private Double produtoAntigoValor;

	@Column(name = "produtoDescricao")
	private String produtoDescricao;

	@Column(name = "categoria_id")
	private Categoria categoria;

	@Column(name = "produtoEstoque")
	private Integer produtoEstoque;

	@Column(name = "produtoStatus")
	private Status status;

	@Column(name = "promocaoStatus")
	private Promocao promocaoStatus;

	@Column(name = "produtoImagem")
	private String produtoImagem;

	@Column(name = "produtoDesconto")
	private Double produtoDesconto;

	@Column(name = "dataCriacao")
	@CreationTimestamp
	private LocalDate dataCriacao  = LocalDate.now();

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column (columnDefinition = "Date", nullable = true)
	private Date dataLimitePromocao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "loja_id")
	private Loja loja;

	public Produto() {
		super();
	}

	public Produto(ProdutoDTO obj) {
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
		this.dataCriacao = obj.getDataCriacao();
		this.dataLimitePromocao = obj.getDataLimitePromocao();
		this.loja = obj.getLoja();
	}

	public Produto(Integer id, String name, Double produtoValor, String produtoDescricao, Categoria categoria,
			Integer produtoEstoque, Status status, Promocao promocaoStatus, String produtoImagem, Double produtoDesconto, Date dataLimitePromocao, Loja loja) {
		super();
		this.id = id;
		this.name = name;
		this.produtoValor = produtoValor;
		this.produtoDescricao = produtoDescricao;
		this.categoria = categoria;
		this.produtoEstoque = produtoEstoque;
		this.status = status;
		this.promocaoStatus = promocaoStatus;
		this.produtoImagem = produtoImagem;
		this.produtoDesconto = produtoDesconto;
		this.dataLimitePromocao = dataLimitePromocao;
		this.loja = loja;
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

		LocalDate dataLimitePromo = dataLimitePromocao.toInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate();
		LocalDate dataAtual = LocalDate.now();

		boolean verificadorDePromocao = dataAtual.isBefore(dataLimitePromo);
		boolean verificadorDePromocao2 = dataAtual.isEqual(dataLimitePromo);

		if((verificadorDePromocao || verificadorDePromocao2) && promocaoStatus == Promocao.ATIVADA) {
			Double desconto = (produtoDesconto / 100) * produtoValor;
			return  produtoValor - desconto;
		}else{
			return produtoValor;
		}
	}

	public void setProdutoValor(Double produtoValor) {
		this.produtoValor = produtoValor;
	}

	public Double getProdutoAntigoValor() {
		return ((produtoValor - produtoValor * (produtoDesconto / 100)) / (1 - produtoDesconto / 100));
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

		LocalDate dataLimitePromo = dataLimitePromocao.toInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime().toLocalDate();
		LocalDate dataAtual = LocalDate.now();

		boolean verificadorDePromocao = dataAtual.isAfter(dataLimitePromo);

		if(verificadorDePromocao && promocaoStatus == Promocao.ATIVADA) {
			return Promocao.DESATIVADA;
		}else{
			return promocaoStatus;
		}
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