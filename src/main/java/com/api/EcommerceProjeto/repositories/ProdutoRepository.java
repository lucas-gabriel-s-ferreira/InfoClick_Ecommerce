package com.api.EcommerceProjeto.repositories;

import com.api.EcommerceProjeto.domain.Produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findById(Integer id);
    @Query(value = "select u from Produto u where lower(trim(u.name)) like %?1%")
   List<Produto> buscarPorNome(String name );
    
    @Query(value = "select u from Produto u where lower(trim(u.categoria)) like %?1%")
    List<Produto> buscarPorCategoria(String categoria );

}