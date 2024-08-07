package br.edu.ifsc.fln.model.domain;

import java.time.LocalDate;

/**
 * Representa um cliente no sistema.
 *  
 * Refatorado por andreriffen
 * 
 * @author mpisch
 */
public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private LocalDate dataNascimento;

    /**
     * Obtém o identificador do cliente.
     * 
     * @return O identificador do cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do cliente.
     * 
     * @param id O identificador a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do cliente.
     * 
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     * 
     * @param nome O nome a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o CPF do cliente.
     * 
     * @return O CPF do cliente.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     * 
     * @param cpf O CPF a ser definido.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o telefone do cliente.
     * 
     * @return O telefone do cliente.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente.
     * 
     * @param telefone O telefone a ser definido.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o endereço do cliente.
     * 
     * @return O endereço do cliente.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do cliente.
     * 
     * @param endereco O endereço a ser definido.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém a data de nascimento do cliente.
     * 
     * @return A data de nascimento do cliente.
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define a data de nascimento do cliente.
     * 
     * @param dataNascimento A data de nascimento a ser definida.
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
