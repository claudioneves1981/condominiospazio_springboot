package br.com.springboot.condominiospazio.service;

import br.com.springboot.condominiospazio.model.Marca;
import br.com.springboot.condominiospazio.model.Modelo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FeignClient(name ="tabelafipe", url = "https://fipe.parallelum.com.br/api/v2/cars")
public interface FipeService {

    @RequestMapping(method = RequestMethod.GET, value = "/brands")
    List<Marca> consultaMarcas();

    @RequestMapping(method = RequestMethod.GET, value = "/brands/{marca}/models")
    List<Modelo> consultaModelos(@PathVariable("marca") String brand);

}