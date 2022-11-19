package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Endereco;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio do endereço. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 * @author maykon
 */

public interface EnderecoService {
        
    Iterable<Endereco> buscarTodos();

    Endereco buscarPorId(String id);

    Endereco inserir(Endereco endereco) throws Exception;

    Endereco atualizar(Endereco endereco);

    void deletar(String id);
}
