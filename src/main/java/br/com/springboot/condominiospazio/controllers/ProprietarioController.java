package br.com.springboot.condominiospazio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.springboot.condominiospazio.model.Proprietario;
import br.com.springboot.condominiospazio.repository.ProprietarioRepository;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {
	
	@Autowired
	private ProprietarioRepository proprietarioRepository;
    /**
     *
     * @return greeting text
     */
    
    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<List<Proprietario>> listaProprietario(){
    	List<Proprietario> proprietarios = proprietarioRepository.findAll();
    	return new ResponseEntity<>(proprietarios, HttpStatus.OK);
    
    }
    
   @PostMapping(value = "/salvar")
   @ResponseBody
    public ResponseEntity<Proprietario> salvarProprietario(@RequestBody Proprietario proprietario){
    	Proprietario prop = proprietarioRepository.save(proprietario);
    	return new ResponseEntity<>(prop, HttpStatus.CREATED);
    }
   
   @DeleteMapping(value = "/delete")
   @ResponseBody
    public ResponseEntity<String> deleteProprietario(@RequestParam Long codigo){
    	proprietarioRepository.deleteById(codigo);
    	return new ResponseEntity<>("Proprietario deletado com sucesso", HttpStatus.OK);
    }
   
   @GetMapping(value = "/buscar")
   @ResponseBody
    public ResponseEntity<Proprietario> buscarProprietarioId(@RequestParam(name = "codigo") Long codigo){
    	Proprietario proprietario = proprietarioRepository.findById(codigo).get();
    	return new ResponseEntity<Proprietario>(proprietario, HttpStatus.OK);
    }
   
   @PutMapping(value = "/atualizar")
   @ResponseBody
    public ResponseEntity<?> atualizarProprietario(@RequestBody Proprietario proprietario){
    	
	   if(proprietario.getCodigo() == null) {
		   return new ResponseEntity<>("Codigo de Proprietário não foi informado para atualização", HttpStatus.OK);
	   }
	   
	   Proprietario user = proprietarioRepository.saveAndFlush(proprietario);
    	return new ResponseEntity<>(user, HttpStatus.OK);
    }
   
   @GetMapping(value = "/buscarpornome")
   @ResponseBody
    public ResponseEntity<List<Proprietario>> buscarProprietarioPorNome(@RequestParam(name = "nome") String nome){
    	List<Proprietario> proprietario = proprietarioRepository.findByNome(nome);
    	return new ResponseEntity<>(proprietario, HttpStatus.OK);
    }
}
