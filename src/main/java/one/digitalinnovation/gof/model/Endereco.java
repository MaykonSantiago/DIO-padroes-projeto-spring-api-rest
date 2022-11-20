package one.digitalinnovation.gof.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Os atributos desse modelo foram gerados automaticamente pelo site
 * jsonschema2pojo.org. Para isso, usamos o JSON de retorno da API do ViaCEP.
 * 
 * @see <a href="https://www.jsonschema2pojo.org/">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * @author maykon
 */

@Entity
public class Endereco {
    
    @Id
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    @OneToMany(mappedBy = "endereco")
    @JsonIgnore
    List<Cliente> cliente;

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public String getCep() {
    return cep;
    }

    public void setCep(String cep) {
    this.cep = cep;
    }

    public String getLogradouro() {
    return logradouro;
    }

    public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
    }

    public String getComplemento() {
    return complemento;
    }

    public void setComplemento(String complemento) {
    this.complemento = complemento;
    }

    public String getBairro() {
    return bairro;
    }

    public void setBairro(String bairro) {
    this.bairro = bairro;
    }

    public String getLocalidade() {
    return localidade;
    }

    public void setLocalidade(String localidade) {
    this.localidade = localidade;
    }

    public String getUf() {
    return uf;
    }

    public void setUf(String uf) {
    this.uf = uf;
    }

    public String getIbge() {
    return ibge;
    }

    public void setIbge(String ibge) {
    this.ibge = ibge;
    }

    public String getGia() {
    return gia;
    }

    public void setGia(String gia) {
    this.gia = gia;
    }

    public String getDdd() {
    return ddd;
    }

    public void setDdd(String ddd) {
    this.ddd = ddd;
    }

    public String getSiafi() {
    return siafi;
    }

    public void setSiafi(String siafi) {
    this.siafi = siafi;
    }

    public Endereco atualizaEndereco (Endereco novoEndereco) {

        if (novoEndereco.getLogradouro() != null){
            this.setLogradouro(novoEndereco.getLogradouro());
        }
        if (novoEndereco.getComplemento() != null){
            this.setComplemento(novoEndereco.getComplemento());
        }
        if (novoEndereco.getBairro() != null){
            this.setBairro(novoEndereco.getBairro());
        }
        if (novoEndereco.getLocalidade() != null){
            this.setLocalidade(novoEndereco.getLocalidade());
        }
        if (novoEndereco.getUf() != null){
            this.setUf(novoEndereco.getUf());
        }
        if (novoEndereco.getIbge() != null){
            this.setIbge(novoEndereco.getIbge());
        }
        if (novoEndereco.getGia() != null){
            this.setGia(novoEndereco.getGia());
        }
        if (novoEndereco.getDdd() != null){
            this.setDdd(novoEndereco.getDdd());
        }
        if (novoEndereco.getSiafi() != null){
            this.setSiafi(novoEndereco.getSiafi());
        }
        if (novoEndereco.getCliente() != null){
            this.cliente.addAll(novoEndereco.getCliente());
        }

        return this;
    }
}
