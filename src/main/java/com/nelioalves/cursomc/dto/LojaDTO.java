package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.nelioalves.cursomc.domain.Loja;

public class LojaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	private String tipo;
	
	private String urlLogo;
	
	private String descricao;
	
	private String funcionamento;
	
	private Double avaliacao;

	public LojaDTO() {
	}
	
	public LojaDTO(Loja obj) {
		id = obj.getId();
		nome = obj.getNome();
		tipo = obj.getTipo();
		urlLogo = obj.getUrlLogo();
		descricao = obj.getDescricao();
		funcionamento = obj.getFuncionamento();
		avaliacao = obj.getAvaliacao();
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
	
	
}
