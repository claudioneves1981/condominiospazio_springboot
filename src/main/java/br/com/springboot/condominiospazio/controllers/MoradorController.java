package br.com.springboot.condominiospazio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.springboot.condominiospazio.model.Morador;
import br.com.springboot.condominiospazio.repository.MoradorRepository;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/morador")
public class MoradorController {

    @Autowired
    private MoradorRepository moradorRepository;

    /**
     * @return greeting text
     */

    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<List<Morador>> listaMorador() {
        List<Morador> moradors = moradorRepository.findAll();
        return new ResponseEntity<>(moradors, HttpStatus.OK);

    }

    @PostMapping(value = "/salvar")
    @ResponseBody
    public ResponseEntity<Morador> salvarMorador(@RequestBody Morador morador) {
        Morador prop = moradorRepository.save(morador);
        return new ResponseEntity<>(prop, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deleteMorador(@RequestParam Long codigo) {
        moradorRepository.deleteById(codigo);
        return new ResponseEntity<>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "/buscar")
    @ResponseBody
    public ResponseEntity<Morador> buscarMoradorId(@RequestParam(name = "codigo") Long codigo) {
        Morador morador = moradorRepository.findById(codigo).get();
        return new ResponseEntity<Morador>(morador, HttpStatus.OK);
    }

    @PutMapping(value = "/atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizarMorador(@RequestBody Morador morador) {

        if (morador.getCodigo() == null) {
            return new ResponseEntity<>("Codigo de Morador não foi informado para atualização", HttpStatus.OK);
        }

        Morador user = moradorRepository.saveAndFlush(morador);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarpornome")
    @ResponseBody
    public ResponseEntity<List<Morador>> buscarMoradorPorNome(@RequestParam(name = "nome") String nome) {
        List<Morador> morador = moradorRepository.findByNome(nome);
        return new ResponseEntity<>(morador, HttpStatus.OK);
    }
}