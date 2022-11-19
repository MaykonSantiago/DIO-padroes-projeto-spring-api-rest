package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.service.EnderecoService;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * 
 * @author maykon
 */

@RestController
@RequestMapping("endereco")
public class EnderecoRestController {

    @Autowired
    private EnderecoService enderecoService;
    
    @GetMapping
    public ResponseEntity<Iterable<Endereco>> buscarTodos() {
        return ResponseEntity.ok(enderecoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Endereco> inserir(@RequestBody Endereco endereco) throws Exception {
        Endereco resposta = enderecoService.inserir(endereco);
        return ResponseEntity.ok(resposta);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@RequestBody Endereco endereco) {
        Endereco resposta = enderecoService.atualizar(endereco);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        enderecoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
