package com.example.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estoque.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos,Long>{
    
}
