package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_TABELAPRECO",
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "tabelapreco_id")
	)
	private List<TabelaPreco> tabelaPrecos = new ArrayList<>();
	
	
	public Produto() {
	}

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;		
	}

	@JsonIgnore
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}
	
	@JsonIgnore
	@Transient
	public List<TabelaPreco> getOfertasProduto() {
		List<TabelaPreco> listaOfertasProduto = new ArrayList<>();
		for (TabelaPreco o : tabelaPrecos) {
			listaOfertasProduto.add((TabelaPreco) o.getProdutos());
		}
		return listaOfertasProduto;
	}
	
	@JsonIgnore
	@Transient
	public Boolean hasOferta() {
		if (getOfertasProduto().isEmpty()) {
			return false;
		}
		return true;
	}
	
	@JsonIgnore
	@Transient
	public double getMenorPreco() {
		List<TabelaPreco> listaOfertasProduto = new ArrayList<>();
		double menorPreco = 0;
		for (TabelaPreco o : tabelaPrecos) {
			listaOfertasProduto.add((TabelaPreco) o.getProdutos());
			Collections.sort(listaOfertasProduto, Comparator.comparing((TabelaPreco tp) -> tp.getPrecoPromocional()));
			
			menorPreco = (double) listaOfertasProduto.get(0).getPrecoPromocional(); 
		}
		return menorPreco;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
		
	public List<TabelaPreco> getTabelaPrecos() {
		return tabelaPrecos;
	}

	public void setTabelaPrecos(List<TabelaPreco> tabelaPrecos) {
		this.tabelaPrecos = tabelaPrecos;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
