package com.example.estoque.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     @PostMapping
    public ResponseEntity<Produtos> criarProdutos(@RequestBody Produtos entity){
        Produtos produtos = repository.save(entity); 
        return ResponseEntity.status(HttpStatus.CREATED).body(produtos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produtos> editarProdutos(@PathVariable Long id,@RequestBody Produtos entity){
        Produtos produtoSalvo = repository.findById(id).orElse(null);
        if(produtoSalvo == null){
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(entity, produtoSalvo,"id");
        return ResponseEntity.ok().body(repository.save(produtoSalvo));
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<Produtos> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
