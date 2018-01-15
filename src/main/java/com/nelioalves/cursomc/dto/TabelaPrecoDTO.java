package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import java.util.Date;

import com.nelioalves.cursomc.domain.Loja;
import com.nelioalves.cursomc.domain.TabelaPreco;

public class TabelaPrecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Loja loja;
	
	private Double precoNormal;
	
	private Double precoPromocional;
	
	private Date dataInicioPromocao;
	
	private Date dataTerminoPromocao;

	public TabelaPrecoDTO() {
	}
	
	public TabelaPrecoDTO(TabelaPreco obj) {
		id = obj.getId();
		loja = obj.getLoja();
		precoNormal = obj.getPrecoNormal();
		precoPromocional = obj.getPrecoPromocional();
		dataInicioPromocao = obj.getDataInicioPromocao();
		dataTerminoPromocao = obj.getDataTerminoPromocao();
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
	
}
