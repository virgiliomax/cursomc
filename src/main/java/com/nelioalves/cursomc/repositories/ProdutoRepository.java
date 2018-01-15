package com.nelioalves.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	       
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

	
	/*@Transactional(readOnly=true)
	Page<Produto> findAllTabelaPrecoIsNotNull(Pageable pageRequest);*/
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT t.produtos from TabelaPreco t")
	//Page<Produto> todasOfertas(Pageable pageRequest);
	List<Produto> todasOfertas();
	
	@Transactional(readOnly=true)
	//@Query("SELECT DISTINCT c.produtos from Categoria c")
	@Query("SELECT DISTINCT c.produtos from Categoria c")
	Page<Produto> todasCategoria(Pageable pageRequest);
	
	
	

}
