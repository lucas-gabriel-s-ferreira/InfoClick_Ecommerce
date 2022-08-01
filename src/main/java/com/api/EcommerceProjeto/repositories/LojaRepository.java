package com.api.EcommerceProjeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.EcommerceProjeto.domain.Loja;

public interface LojaRepository extends JpaRepository<Loja, Integer> {

	Optional<Loja> findById(Integer id);

	@Query(value = "SELECT l FROM Loja l INNER JOIN Pessoa p ON l.empreendedor = p.id AND p.email = ?1")
	Optional<Loja> findLojaByEmail(String email);
}
