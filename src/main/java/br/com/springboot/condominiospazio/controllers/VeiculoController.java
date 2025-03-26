package br.com.springboot.condominiospazio.controllers;

import java.util.List;

import br.com.springboot.condominiospazio.service.VeiculoService;
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
    private VeiculoService veiculoService;

    /**
     * @return greeting text
     */

    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<List<Veiculo>> listaVeiculos() {
        List<Veiculo> veiculos = veiculoService.listaVeiculos();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);

    }

    @PostMapping(value = "/salvar/{cpf}")
    @ResponseBody
    public ResponseEntity<String> salvarVeiculo(@PathVariable(value = "cpf") String cpf, @RequestBody Veiculo veiculo) {
        veiculoService.salvarVeiculo(cpf, veiculo);
        return new ResponseEntity<>("Veiculo Salvo com Sucesso", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{cpf}/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteVeiculo(@PathVariable String cpf, @PathVariable Long id) {
        veiculoService.deleteVeiculo(cpf,id);
        return new ResponseEntity<>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "/buscar")
    @ResponseBody
    public ResponseEntity<Veiculo> buscarVeiculoId(@RequestParam(name = "codigo") Long codigo) {
        Veiculo veiculo = veiculoService.buscarVeiculoId(codigo);
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
    }

    /*@PutMapping(value = "/atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizarVeiculo(@RequestBody Veiculo veiculo) {

        if (veiculo.getId() == null) {
            return new ResponseEntity<>("Codigo de Veiculo não foi informado para atualização", HttpStatus.OK);
        }

        Veiculo user = veiculoRepository.saveAndFlush(veiculo);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/

    @GetMapping(value = "/buscarporplaca")
    @ResponseBody
    public ResponseEntity<Veiculo> buscarVeiculoPorPlaca(@RequestParam(name = "placa") String placa) {
       Veiculo veiculo = veiculoService.buscarVeiculoPorPlaca(placa);
        return new ResponseEntity<>(veiculo, HttpStatus.OK);
    }
}