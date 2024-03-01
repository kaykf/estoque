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
import com.example.estoque.model.Estoque;
import com.example.estoque.repository.EstoqueRepository;

@CrossOrigin
@RestController
@RequestMapping("/estoque")

public class EstoqueController {

    @Autowired
    private EstoqueRepository repository;

    @GetMapping
    public List<Estoque>listar() {
        return repository.findAll();
    }
     @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarPorCodigo(@PathVariable Long id) {
        Estoque estoque = repository.findById(id).orElse(null);
        return estoque != null ? ResponseEntity.ok().body(estoque):
        ResponseEntity.notFound().build();
    }
     @PostMapping
    public ResponseEntity<Estoque> criarProdutos(@RequestBody Estoque entity){
        Estoque estoque = repository.save(entity); 
        return ResponseEntity.status(HttpStatus.CREATED).body(estoque);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Estoque> editarProdutos(@PathVariable Long id,@RequestBody Estoque entity){
        Estoque estoqueSalvo = repository.findById(id).orElse(null);
        if(estoqueSalvo == null){
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(entity, estoqueSalvo,"id");
        return ResponseEntity.ok().body(repository.save(estoqueSalvo));
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<Estoque> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
