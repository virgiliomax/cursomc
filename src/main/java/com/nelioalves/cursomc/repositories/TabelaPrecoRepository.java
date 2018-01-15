package com.nelioalves.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.Loja;
import com.nelioalves.cursomc.domain.TabelaPreco;

@Repository
public interface TabelaPrecoRepository extends JpaRepository<TabelaPreco, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM TabelaPreco obj INNER JOIN obj.produtos prod")
	Page<TabelaPreco> todasOfertas(Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM TabelaPreco obj INNER JOIN obj.produtos prod WHERE loja_id = :id")
	Page<TabelaPreco> ofertasLoja(@Param("id") Loja loja, Pageable pageRequest);
}
