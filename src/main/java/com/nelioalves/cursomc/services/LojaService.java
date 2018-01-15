package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Loja;
import com.nelioalves.cursomc.dto.LojaDTO;
import com.nelioalves.cursomc.repositories.LojaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class LojaService {
	
	@Autowired
	private LojaRepository repo;
	
	public Loja find(Integer id) {
		Loja obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Loja.class.getName());
		}
		return obj;
	}
	
	public Loja insert(Loja obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Loja update(Loja obj) {
		Loja newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma LOJA que possui DADOS relacionados.");
		}
	}
	
	public List<Loja> findAll() {
		return repo.findAll();
	}
	
	public Page<Loja> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Loja fromDTO(LojaDTO objDto) {
		return new Loja(objDto.getId(), null, null, objDto.getNome(), objDto.getDescricao(), objDto.getFuncionamento(), objDto.getAvaliacao());
	}

	private void updateData(Loja newObj, Loja obj) {
		newObj.setNome(obj.getNome());
	}
}
