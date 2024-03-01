package com.example.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estoque.model.Funcionarios;

public interface FuncionariosRepository extends JpaRepository<Funcionarios,Long>{
    
}
