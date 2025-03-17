package br.com.springboot.condominiospazio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.springboot.condominiospazio.model.Veiculo;
import br.com.springboot.condominiospazio.repository.VeiculoRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    /**
     * @return greeting text
     */

    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<List<Veiculo>> listaVeiculo() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);

    }

    @PostMapping(value = "/salvar")
    @ResponseBody
    public ResponseEntity<Veiculo> salvarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo prop = veiculoRepository.save(veiculo);
        return new ResponseEntity<>(prop, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deleteVeiculo(@RequestParam Long codigo) {
        veiculoRepository.deleteById(codigo);
        return new ResponseEntity<>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "/buscar")
    @ResponseBody
    public ResponseEntity<Veiculo> buscarVeiculoId(@RequestParam(name = "codigo") Long codigo) {
        Veiculo veiculo = veiculoRepository.findById(codigo).get();
        return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
    }

    @PutMapping(value = "/atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizarVeiculo(@RequestBody Veiculo veiculo) {

        if (veiculo.getId() == null) {
            return new ResponseEntity<>("Codigo de Veiculo não foi informado para atualização", HttpStatus.OK);
        }

        Veiculo user = veiculoRepository.saveAndFlush(veiculo);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/buscarporplaca")
    @ResponseBody
    public ResponseEntity<List<Veiculo>> buscarVeiculoPorPlaca(@RequestParam(name = "placa") String placa) {
        List<Veiculo> veiculo = veiculoRepository.findByPlaca(placa);
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
    }
}