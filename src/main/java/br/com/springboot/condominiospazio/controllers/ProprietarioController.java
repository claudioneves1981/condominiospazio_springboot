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
public class ProprietarioController {
	
	@Autowired
	private ProprietarioRepository proprietarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    
    @GetMapping(value = "proprietariolistatodos")
    @ResponseBody
    public ResponseEntity<List<Proprietario>> listaUsuario(){
    	List<Proprietario> proprietarios = proprietarioRepository.findAll();
    	return new ResponseEntity<List<Proprietario>>(proprietarios, HttpStatus.OK);
    
    }
    
   @PostMapping(value = "proprietariosalvar")
   @ResponseBody
    public ResponseEntity<Proprietario> salvar(@RequestBody Proprietario proprietario){
    	Proprietario prop = proprietarioRepository.save(proprietario);
    	return new ResponseEntity<Proprietario>(prop, HttpStatus.CREATED);
    }
   
   @DeleteMapping(value = "proprietariodelete")
   @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	proprietarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }
   
   @GetMapping(value = "proprietariobuscaruserId")
   @ResponseBody
    public ResponseEntity<Proprietario> buscaruserId(@RequestParam(name = "iduser") Long iduser){
    	Proprietario proprietario = proprietarioRepository.findById(iduser).get();
    	return new ResponseEntity<Proprietario>(proprietario, HttpStatus.OK);
    }
   
   @PutMapping(value = "proprietarioatualizar")
   @ResponseBody
    public ResponseEntity<?> buscaruserId(@RequestBody Proprietario proprietario){
    	
	   if(proprietario.getCodigo() == null) {
		   return new ResponseEntity<String>("Codigo de Proprietário não foi informado para atualização", HttpStatus.OK);
	   }
	   
	   Proprietario user = proprietarioRepository.saveAndFlush(proprietario);
    	return new ResponseEntity<Proprietario>(user, HttpStatus.OK);
    }
   
   @GetMapping(value = "proprietariobuscarPorNome")
   @ResponseBody
    public ResponseEntity<List<Proprietario>> buscarPorNome(@RequestParam(name = "name") String name){
    	List<Proprietario> proprietario = proprietarioRepository.buscarPorNome(name.trim().toUpperCase());
    	return new ResponseEntity<List<Proprietario>>(proprietario, HttpStatus.OK);
    }
}
