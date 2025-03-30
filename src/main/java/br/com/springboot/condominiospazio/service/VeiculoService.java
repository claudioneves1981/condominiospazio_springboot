package br.com.springboot.condominiospazio.service;


import br.com.springboot.condominiospazio.model.Veiculo;

import java.util.List;

public interface VeiculoService {


    void salvarVeiculo(String cpf, Veiculo veiculo);

    List<Veiculo> listaVeiculos();

    void deleteVeiculo(Long id_morador, Long id);

    Veiculo buscarVeiculoId(Long id);

    Veiculo buscarVeiculoPorPlaca(String placa);

    List<Veiculo> buscarVeiculoPorMorador(String morador);

}
