package br.com.uniamerica.estacionamento.controller;
import br.com.uniamerica.estacionamento.Entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping (value = "/api/marca")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private MarcaRepository marcaRepository;
    @GetMapping("/lista")
    public ResponseEntity<List<Marca>> listaMarca(){
        List<Marca> listartudo = marcaService.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaMarcaId(@PathVariable(value = "id") Long id){
        Marca listarid = marcaRepository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<Marca>> listaMarcaAtivo(@PathVariable boolean ativo) {
        List<Marca> listarAtivo = marcaRepository.findByAtivo(ativo);
        return ResponseEntity.ok(listarAtivo);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Marca cadastro){
        try{
            this.marcaService.cadastrar(cadastro);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("A marca já existe");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro:" + e.getStackTrace());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Long id){
        Optional<Marca> deletarId = marcaRepository.findById(id);
        if (deletarId.isPresent()) {
            marcaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<?> editar (@RequestParam("id") final Long id, @RequestBody final Marca marca){
        try{
            this.marcaService.atualizar(id,marca);
            return ResponseEntity.ok().body("Salvo com sucesso");
        }catch(DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }catch(RuntimeException e){
            return  ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
    }

}
