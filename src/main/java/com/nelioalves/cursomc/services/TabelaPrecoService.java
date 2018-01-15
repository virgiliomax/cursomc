package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Loja;
import com.nelioalves.cursomc.domain.TabelaPreco;
import com.nelioalves.cursomc.repositories.TabelaPrecoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class TabelaPrecoService {
	
	@Autowired
	private TabelaPrecoRepository repo;
	
	public TabelaPreco find(Integer id) {
		TabelaPreco obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + TabelaPreco.class.getName());
		}
		return obj;
	}
	
	public List<TabelaPreco> findAll() {
		return repo.findAll();
	}
	
	public TabelaPreco insert(TabelaPreco obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Page<TabelaPreco> search(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.todasOfertas(pageRequest);	
	}
	
	public Page<TabelaPreco> ofertasLoja(Loja id, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.ofertasLoja(id, pageRequest);	
	}
	
	
	
}
