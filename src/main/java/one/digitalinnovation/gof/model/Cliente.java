package one.digitalinnovation.gof.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "É preciso digitar um nome.")
    private String nome;

    @ManyToOne(cascade = CascadeType.DETACH)
    @NotNull(message = "É preciso digitar um endereço.")
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente atualizaCliente (Cliente novoCliente) {

        if (novoCliente.getId() != null){
            this.setId(novoCliente.getId());
        }
        if (novoCliente.getNome() != null){
            this.setNome(novoCliente.getNome());
        }
        if (novoCliente.getEndereco() != null){
            this.setEndereco(novoCliente.getEndereco());
        }

        return this;
    }

}
