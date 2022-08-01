package com.api.EcommerceProjeto.repositories;

import com.api.EcommerceProjeto.domain.Consumidor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumidorRepository extends JpaRepository<Consumidor, Integer> {

     Optional<Consumidor> findByCpf(String cpf);

     Optional<Consumidor> findById(Integer id);

     Optional<Consumidor> findByEmail(String email);

     @Query(value = "select e.id from Consumidor e where e.email = ?1")
     Integer findIdByEmail(String email);

}
