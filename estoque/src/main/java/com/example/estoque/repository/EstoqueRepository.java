package com.example.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estoque.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque,Long>{
    
}
