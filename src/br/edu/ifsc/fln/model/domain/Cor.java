/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author Andre Riffen
 */
public class Cor {
    
    private int id;
    private String nome;

    /**
     * Construtor padrão sem parâmetros.
     */
    public Cor() {
    }

    /**
     * Construtor que inicializa o nome da cor.
     *
     * @param nome O nome da cor.
     */
    public Cor(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o ID da cor.
     *
     * @return O ID da cor.
     */
    public int getIdCor() {
        return id;
    }

    /**
     * Define o ID da cor.
     *
     * @param id O ID da cor.
     */
    public void setIdCor(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome da cor.
     *
     * @return O nome da cor.
     */
    public String getNomeCor() {
        return nome;
    }

    /**
     * Define o nome da cor.
     *
     * @param nome O nome da cor.
     */
    public void setNomeCor(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna uma representação em string do objeto Cor.
     *
     * @return Uma representação em string do objeto Cor.
     */
    @Override
    public String toString() {
        return "Cor{" + "id=" + id + ", nome=" + nome + '}';
    }
    
}
