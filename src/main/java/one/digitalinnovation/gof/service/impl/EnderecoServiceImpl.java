package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.EnderecoService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link EnderecoService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author maykon
 */

@Service
public class EnderecoServiceImpl implements EnderecoService{

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Endereco> buscarTodos() {
        
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco buscarPorId(String id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.get();
    }

    @Override
    public Endereco inserir(Endereco endereco) throws Exception {
        Optional<Endereco> enderecoBd = enderecoRepository.findById(endereco.getCep());
        if(enderecoBd.isPresent()){
            throw new Exception("Este endereco ja existe, e possivel atualizar.");
        }
        try{
            String cep = endereco.getCep().replace( "-" , "");
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Endereco atualizar(Endereco endereco) {
        Endereco enderecoBd = enderecoRepository.findById(endereco.getCep())
            .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));

        enderecoBd = enderecoBd.atualizaEndereco(endereco);
        enderecoRepository.save(enderecoBd);

        return enderecoRepository.findById(endereco.getCep()).get();
    }

    @Override
    public void deletar(String id) {
        enderecoRepository.deleteById(id);
    }
    
}
