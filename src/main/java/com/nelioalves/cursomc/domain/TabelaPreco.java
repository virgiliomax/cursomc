package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class TabelaPreco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="loja_id")
	private Loja loja;
	
	@ManyToMany(mappedBy="tabelaPrecos")
	private List<Produto> produtos = new ArrayList<>();
	
	private Double precoPromocional;
	
	private Double precoNormal;
	
	private Date dataInicioPromocao;
	
	private Date dataTerminoPromocao;
	
	// Construtor Padrão sem Parâmetros.
	public TabelaPreco() {
	}
	
	// Construtor Todos os Campos.
	public TabelaPreco(Integer id, Loja loja,  Double precoNormal, Double precoPromocional, Date dataInicioPromocao, Date dataTerminoPromocao) {
		super();
		this.id = id;
		this.loja = loja;
		this.precoNormal = precoNormal;
		this.precoPromocional = precoPromocional;
		this.dataInicioPromocao = dataInicioPromocao;
		this.dataTerminoPromocao = dataTerminoPromocao;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
		
	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Double getPrecoPromocional() {
		return precoPromocional;
	}
	
	public void setPrecoPromocional(Double precoPromocional) {
		this.precoPromocional = precoPromocional;
	}
	
	public Double getPrecoNormal() {
		return precoNormal;
	}
	
	public void setPrecoNormal(Double precoNormal) {
		this.precoNormal = precoNormal;
	}
	
	public Date getDataInicioPromocao() {
		return dataInicioPromocao;
	}
	
	public void setDataInicioPromocao(Date dataInicioPromocao) {
		this.dataInicioPromocao = dataInicioPromocao;
	}
	
	public Date getDataTerminoPromocao() {
		return dataTerminoPromocao;
	}
	
	public void setDataTerminoPromocao(Date dataTerminoPromocao) {
		this.dataTerminoPromocao = dataTerminoPromocao;
	}
	
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
		TabelaPreco other = (TabelaPreco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
