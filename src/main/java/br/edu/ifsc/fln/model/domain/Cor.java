package br.edu.ifsc.fln.model.domain;

/**
 *
 * @author andreriffen
 */
public class Cor {
    
    private int id;
    private String nome;

    public Cor() {
    }

    public Cor(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}