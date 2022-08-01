package com.api.EcommerceProjeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.EcommerceProjeto.domain.Empreendedor;

public interface EmpreendedorRepository extends JpaRepository<Empreendedor, Integer> {
	
	Optional<Empreendedor> findByCnpj(String cnpj);
    Optional<Empreendedor> findByEmail(String cnpj);
    Optional<Empreendedor> findById(Integer id);

    @Query(value = "select e.id from Empreendedor e where e.email = ?1")
    Integer findIdByEmail(String email);
}
