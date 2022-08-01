package com.api.EcommerceProjeto.repositories;

import com.api.EcommerceProjeto.domain.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
