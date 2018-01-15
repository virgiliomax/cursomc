package com.nelioalves.cursomc.resources;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.TabelaPreco;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.dto.ProdutoDTO;
import com.nelioalves.cursomc.dto.TabelaPrecoDTO;
import com.nelioalves.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	// Exibe um determinado Produto por ID.
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Exibe todas as Categorias Encontradas de um determinando Produto.
	@RequestMapping(value="/{id}/categorias", method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findCategorias(@PathVariable Integer id) {
	    Produto prod = service.find(id);
	    List<Categoria> cats = prod.getCategorias();
	    List<CategoriaDTO> catsDto = cats.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	    return ResponseEntity.ok().body(catsDto);
	}
	
	// Exibe todas as Ofertas Encontradas de um determinando Produto.
	@RequestMapping(value="/{id}/ofertas", method=RequestMethod.GET)
	public ResponseEntity<List<TabelaPrecoDTO>> findOfertas(@PathVariable Integer id) {
		Produto prod = service.find(id);
		List<TabelaPreco> tps = prod.getTabelaPrecos();
		// Ordena Lista pelo Preco Promocional - Menor Primeiro
		Collections.sort(tps, Comparator.comparing((TabelaPreco tp) -> tp.getPrecoPromocional()));
		// Inverte a Ordenação anterior - Maior Primeiro
		Collections.reverse(tps);
		List<TabelaPrecoDTO> tpsDto = tps.stream().map(obj -> new TabelaPrecoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(tpsDto);
	}
	
	// Exibe o Número Total de Ofertas Encontradas de um determinando Produto.
	@RequestMapping(value="/{id}/totalofertas", method=RequestMethod.GET)
	public ResponseEntity<Integer> countOfertas(@PathVariable Integer id) {
		int obj = service.find(id).getTabelaPrecos().size();
		return ResponseEntity.ok().body(obj);
	}
	
	// Exibe a Maior Oferta Encontrada de um determinando Produto.
	@RequestMapping(value="/{id}/maior", method=RequestMethod.GET)
	public ResponseEntity<TabelaPreco> maiorOfertas(@PathVariable Integer id) {
		Produto prod = service.find(id);
		List<TabelaPreco> tps = prod.getTabelaPrecos();
		TabelaPreco tpsDto = tps.stream().max(Comparator.comparing((TabelaPreco tp) -> tp.getPrecoPromocional())).get();
		return ResponseEntity.ok().body(tpsDto);
	}
	
	// Exibe a Menor Oferta Encontrada de um determinando Produto.
	@RequestMapping(value="/{id}/menor", method=RequestMethod.GET)
	public ResponseEntity<TabelaPreco> menorOfertas(@PathVariable Integer id) {
		Produto prod = service.find(id);
		List<TabelaPreco> tps = prod.getTabelaPrecos();
		TabelaPreco tpsDto = tps.stream().min(Comparator.comparing((TabelaPreco tp) -> tp.getPrecoPromocional())).get();
		return ResponseEntity.ok().body(tpsDto);
	}
	
	// Lista todos os Produtos Encontrados
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	// Lista todos os Produtos Encontrados
	@RequestMapping(value="/lista",method=RequestMethod.GET)
	public ResponseEntity<List<Produto>> ofertas() {
		List<Produto> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	// Lista Produto em um Categoria
/*	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}*/
	
	@RequestMapping(value="/categorias",method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Produto> list = service.categorias(page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}

	
/*	@RequestMapping(value="/ofertas",method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> todasOfertas(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="produtos") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Produto> listaOferta = service.ofertas(page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = listaOferta.map(obj -> new ProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}*/
	@RequestMapping(value="/emoferta",method=RequestMethod.GET)
	public ResponseEntity<List<Produto>> todasOfertas() {
		List<Produto> listaOferta = service.getOfertas();
		Collections.sort(listaOferta, Comparator.comparing((Produto pro) -> pro.getNome()));
		return ResponseEntity.ok().body(listaOferta);
	}
	
}
