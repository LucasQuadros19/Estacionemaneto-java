package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.Entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(this.Service.listaCondutoresAtivos());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Condutor cadastro){
        try{
            this.Service.cadastrar(cadastro);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("A Condutor já existe");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro:" + e.getStackTrace());
        }
    }

    @DeleteMapping("/delete/{id}")
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
