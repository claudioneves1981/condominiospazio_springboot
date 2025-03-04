package br.com.springboot.condominiospazio.controllers;

import java.util.List;

import br.com.springboot.condominiospazio.model.Visitante;
import br.com.springboot.condominiospazio.model.Visitante;
import br.com.springboot.condominiospazio.repository.VisitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/visitante")
public class VisitanteController {
	
	@Autowired
	private VisitanteRepository visitanteRepository;
    /**
     *
     * @return greeting text
     */

    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<List<Visitante>> listaVisitante(){
        List<Visitante> visitantes = visitanteRepository.findAll();
        return new ResponseEntity<>(visitantes, HttpStatus.OK);

    }


   @PostMapping(value = "/salvar")
   @ResponseBody
    public ResponseEntity<Visitante> salvar(@RequestBody Visitante visitante){
    	Visitante user = visitanteRepository.save(visitante);
    	return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
   
   @DeleteMapping(value = "/delete")
   @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long codigo){
    	visitanteRepository.deleteById(codigo);
    	return new ResponseEntity<>("Visitante deletado com sucesso", HttpStatus.OK);
    }
   
   @GetMapping(value = "/buscar")
   @ResponseBody
    public ResponseEntity<Visitante> buscar(@RequestParam(name = "codigo") Long codigo){
    	Visitante visitante = (Visitante) visitanteRepository.findById(codigo).get();
    	return new ResponseEntity<>(visitante, HttpStatus.OK);
    }
   
   @PutMapping(value = "/atualizar")
   @ResponseBody
   public ResponseEntity<?> atualizar(@RequestBody Visitante visitante){
    	
	   if(visitante.getCodigo() == null) {
		   return new ResponseEntity<>("Id não foi informado para atualização", HttpStatus.OK);
	   }
	   
	    Visitante user = visitanteRepository.saveAndFlush(visitante);
    	return new ResponseEntity<Visitante>(user, HttpStatus.OK);
    }
   
   @GetMapping(value = "/buscarpornome")
   @ResponseBody
    public ResponseEntity<List<Visitante>> buscarPorNome(@RequestParam(name = "nome") String nome){
    	List<Visitante> visitante = visitanteRepository.findByNome(nome.trim().toUpperCase());
    	return new ResponseEntity<List<Visitante>>(visitante, HttpStatus.OK);
    }
}
