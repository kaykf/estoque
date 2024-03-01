package com.example.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.estoque.model.Produtos;
import com.example.estoque.repository.ProdutosRepository;

@CrossOrigin
@RestController
@RequestMapping("/produtos")

public class ProdutosController {
    
    @Autowired
    private ProdutosRepository repository ;

    @GetMapping
    public List<Produtos>listar() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produtos> buscarPorCodigo(@PathVariable Long id) {
        Produtos produtos = repository.findById(id).orElse(null);
        return produtos != null ? ResponseEntity.ok().body(produtos):
        ResponseEntity.notFound().build();
    }
}
