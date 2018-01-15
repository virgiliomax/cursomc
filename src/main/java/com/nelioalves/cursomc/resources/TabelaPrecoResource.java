package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Loja;
import com.nelioalves.cursomc.domain.TabelaPreco;
import com.nelioalves.cursomc.dto.TabelaPrecoDTO;
import com.nelioalves.cursomc.services.TabelaPrecoService;

@RestController
@RequestMapping(value="/ofertas")
public class TabelaPrecoResource {
	
	@Autowired
	private TabelaPrecoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TabelaPreco> find(@PathVariable Integer id) {
		TabelaPreco obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TabelaPreco obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TabelaPreco>> findAll() {
		List<TabelaPreco> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/todas",method=RequestMethod.GET)
	public ResponseEntity<Page<TabelaPrecoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="precoPromocional") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
	
		Page<TabelaPreco> list = service.search(page, linesPerPage, orderBy, direction);
		Page<TabelaPrecoDTO> listDto = list.map(obj -> new TabelaPrecoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/loja",method=RequestMethod.GET)
	public ResponseEntity<Page<TabelaPrecoDTO>> ofertasLoja(
			@RequestParam(value="id", defaultValue="") Loja id,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="precoPromocional") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
	
		Page<TabelaPreco> list = service.ofertasLoja(id, page, linesPerPage, orderBy, direction);
		Page<TabelaPrecoDTO> listDto = list.map(obj -> new TabelaPrecoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
}
