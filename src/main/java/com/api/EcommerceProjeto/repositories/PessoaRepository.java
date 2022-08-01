package com.api.EcommerceProjeto.repositories;

import com.api.EcommerceProjeto.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

        Optional<Pessoa> findByEmail(String email);
}


