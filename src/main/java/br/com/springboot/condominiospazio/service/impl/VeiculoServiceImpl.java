package br.com.springboot.condominiospazio.service.impl;

import br.com.springboot.condominiospazio.exceptions.PessoaNaoEncontradaException;
import br.com.springboot.condominiospazio.exceptions.VeiculoNaoEncontradoException;
import br.com.springboot.condominiospazio.model.Morador;
import br.com.springboot.condominiospazio.model.Veiculo;
import br.com.springboot.condominiospazio.repository.MoradorRepository;
import br.com.springboot.condominiospazio.repository.VeiculoRepository;
import br.com.springboot.condominiospazio.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {


    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public void salvarVeiculo(String cpf, Veiculo veiculo) {

        try{

            Morador morador = moradorRepository.findByDocumento(cpf);
            List<Veiculo> veiculoList = morador.getVeiculos();


            if(veiculo.getId() == null) {

                veiculo.setMorador(morador.getNome());
                veiculoList.add(veiculo);
                morador.setVeiculos(veiculoList);
                moradorRepository.save(morador);

            } else {

                for(Veiculo veiculotemp : veiculoList){

                    if (Objects.equals(veiculotemp.getId(), veiculo.getId())){
                        veiculoList.remove(veiculotemp);
                        veiculoRepository.delete(veiculotemp);
                        veiculo.setMorador(morador.getNome());
                        veiculoList.add(veiculo);
                        morador.setVeiculos(veiculoList);
                        moradorRepository.save(morador);
                        break;
                    }

                }

            }

        } catch(PessoaNaoEncontradaException re){
            throw re;
        }

    }

    @Override
    public List<Veiculo> listaVeiculos() {
          return veiculoRepository.findAll();
    }

    @Override
    public void deleteVeiculo(String cpf, Long id) throws VeiculoNaoEncontradoException {

        Morador morador = moradorRepository.findByDocumento(cpf);
        Veiculo veiculo = veiculoRepository.findById(id).
                orElseThrow(()-> new VeiculoNaoEncontradoException("Vehicle Not Found"));
        morador.getVeiculos().remove(veiculo);
        veiculoRepository.delete(veiculo);
        moradorRepository.save(morador);
    }

    @Override
    public Veiculo buscarVeiculoId(Long id) {
        final Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        if(optionalVeiculo.isPresent()){
            return optionalVeiculo.get();
        }else{
            throw new VeiculoNaoEncontradoException("Vehicle with id"+id+" not found");
        }
    }

    @Override
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa);
    }


}
