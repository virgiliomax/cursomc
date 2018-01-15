package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Loja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String tipo;
	
	private String urlLogo;
	
	private String nome;	
	
	private String descricao;
	
	private String funcionamento;
	
	private Double avaliacao;
	
	@JsonIgnore
	@OneToMany(mappedBy="loja")
	private List<TabelaPreco> ofertas = new ArrayList<>();
	
	/*@JsonIgnore
	@OneToMany(mappedBy="loja")
	private List<POferta> pofertas = new ArrayList<>();*/
	
	// Construtor Padrão sem Argumentos
	public Loja() {
	}
	
	// Construtor com Todos os Campos
	public Loja(Integer id, String tipo, String urlLogo, String nome, String descricao, String funcionamento,
			double avaliacao) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.urlLogo = urlLogo;
		this.nome = nome;
		this.descricao = descricao;
		this.funcionamento = funcionamento;
		this.avaliacao = avaliacao;
	}

	// Getters e Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFuncionamento() {
		return funcionamento;
	}

	public void setFuncionamento(String funcionamento) {
		this.funcionamento = funcionamento;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public List<TabelaPreco> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<TabelaPreco> ofertas) {
		this.ofertas = ofertas;
	}

	// HasCode e Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loja other = (Loja) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
