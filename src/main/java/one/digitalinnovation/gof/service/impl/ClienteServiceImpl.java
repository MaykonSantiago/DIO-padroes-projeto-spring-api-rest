package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.webjars.NotFoundException;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.EnderecoService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author maykon
 */

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public Cliente inserir(Cliente cliente) throws Exception {
        try{
            Optional<Endereco> endereco = enderecoRepository.findById(cliente.getEndereco().getCep());
            if(endereco.isEmpty()) {
                enderecoService.inserir(cliente.getEndereco());
                cliente.setEndereco(enderecoRepository.findById(cliente.getEndereco().getCep()).get());
                clienteRepository.save(cliente);
                return clienteRepository.findById(cliente.getId()).get();
            }

            cliente.setEndereco(endereco.get());
            clienteRepository.save(cliente);

            return clienteRepository.findById(cliente.getId()).get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente atualizar(Cliente cliente) throws Exception {
        Cliente clienteBd = clienteRepository.findById(cliente.getId())
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        
        Optional<Endereco> endereco = enderecoRepository.findById(cliente.getEndereco().getCep());
        try{
            if(endereco.isEmpty()) {
                Endereco enderecoSalvo = enderecoService.inserir(cliente.getEndereco());
                cliente.setEndereco(enderecoSalvo);
            }

            clienteBd = clienteBd.atualizaCliente(cliente);
            clienteRepository.save(clienteBd);
            
            return clienteRepository.findById(cliente.getId()).get();
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
    
}
