/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * Representa um veículo.
 * 
 * @author Andre Riffen
 * 
 */
public class Veiculo {
    
    private int id;
    private String placa;
    private String observacoes;
    
    private Modelo modelo;
    private Cor cor;

    /**
     * Construtor padrão sem parâmetros.
     */
    public Veiculo() {
    }

    /**
     * Construtor que inicializa a placa do veículo.
     *
     * @param placa A placa do veículo.
     */
    public Veiculo(String placa) {
        this.placa = placa;
    }

    /**
     * Construtor que inicializa a placa e o modelo do veículo.
     *
     * @param placa A placa do veículo.
     * @param modelo O modelo do veículo.
     */
    public Veiculo(String placa, Modelo modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    /**
     * Obtém o ID do veículo.
     *
     * @return O ID do veículo.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do veículo.
     *
     * @param id O ID do veículo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém a placa do veículo.
     *
     * @return A placa do veículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa do veículo.
     *
     * @param placa A placa do veículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtém as observações do veículo.
     *
     * @return As observações do veículo.
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * Define as observações do veículo.
     *
     * @param observacoes As observações do veículo.
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * Obtém o modelo do veículo.
     *
     * @return O modelo do veículo.
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do veículo.
     *
     * @param modelo O modelo do veículo.
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém a cor do veículo.
     *
     * @return A cor do veículo.
     */
    public Cor getCor() {
        return cor;
    }

    /**
     * Define a cor do veículo.
     *
     * @param cor A cor do veículo.
     */
    public void setCor(Cor cor) {
        this.cor = cor;
    }
        
}
