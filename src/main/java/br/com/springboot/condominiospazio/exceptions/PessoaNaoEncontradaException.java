package br.com.springboot.condominiospazio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PessoaNaoEncontradaException extends RuntimeException {

  public PessoaNaoEncontradaException(String message){
    super(message);
  }

}
