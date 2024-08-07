package br.edu.ifsc.fln.model.domain;

/**
 * Representa um cliente no sistema.
 *  
 * Refatorado por andreriffen
 * 
 * @author mpisch
 */
public class Marca {

    private int id;
    private String nome;

    /**
     * Obtém o identificador da marca.
     * 
     * @return O identificador da marca.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da marca.
     * 
     * @param id O identificador a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome da marca.
     * 
     * @return O nome da marca.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da marca.
     * 
     * @param nome O nome a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Marca{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome);
        sb.append('}');
        return sb.toString();
    }
}
