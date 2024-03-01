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
import com.example.estoque.model.Funcionarios;
import com.example.estoque.repository.FuncionariosRepository;

@CrossOrigin
@RestController
@RequestMapping("/funcionarios")

public class FuncionariosController {

    @Autowired
    private FuncionariosRepository repository;

    @GetMapping
    public List<Funcionarios>listar() {
        return repository.findAll();
    }
     @GetMapping("/{id}")
    public ResponseEntity<Funcionarios> buscarPorCodigo(@PathVariable Long id) {
        Funcionarios funcionarios = repository.findById(id).orElse(null);
        return funcionarios != null ? ResponseEntity.ok().body(funcionarios):
        ResponseEntity.notFound().build();
    }
     @PostMapping
    public ResponseEntity<Funcionarios> criarProdutos(@RequestBody Funcionarios entity){
        Funcionarios funcionarios = repository.save(entity); 
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarios);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Funcionarios> editarProdutos(@PathVariable Long id,@RequestBody Funcionarios entity){
        Funcionarios funcionariosSalvo = repository.findById(id).orElse(null);
        if(funcionariosSalvo == null){
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(entity, funcionariosSalvo,"id");
        return ResponseEntity.ok().body(repository.save(funcionariosSalvo));
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<Funcionarios> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
