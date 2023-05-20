package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.Entity.Condutor;
import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.Entity.Modelo;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/condutor")
public class CondutorController {

    @Autowired
    private CondutorService Service;

    @Autowired
    private CondutorRepository Repository;

    @GetMapping("/lista")
    public ResponseEntity<List<Condutor>> lista(){
        return ResponseEntity.ok(this.Service.listaCompleta());
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Condutor listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }

    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<Condutor>> listaAtivo(@PathVariable boolean ativo) {
        List<Condutor> listarAtivo = Repository.findByAtivo(ativo);
        return ResponseEntity.ok(listarAtivo);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar (@RequestBody final Condutor condutor){
        try{
            this.Service.cadastrar(condutor);
            return ResponseEntity.ok("Salvo com sucesso");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("error " + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Long id){
        Optional<Condutor> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idCondutor
    ){
        this.Service.desativar(idCondutor);
        return ResponseEntity.ok().body("desativado com sucesso!");
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Condutor atualizarId
    ) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
