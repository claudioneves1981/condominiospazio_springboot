package br.com.springboot.condominiospazio.service;


import br.com.springboot.condominiospazio.model.Veiculo;

import java.util.List;

public interface VeiculoService {


    void salvarVeiculo(String cpf, Veiculo veiculo);

    List<Veiculo> listaVeiculos();

    void deleteVeiculo(String cpf, Long id);

    Veiculo buscarVeiculoId(Long id);

    Veiculo buscarVeiculoPorPlaca(String placa);

}
