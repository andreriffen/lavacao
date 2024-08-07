package br.edu.ifsc.fln.model.domain;

/**
 *
 * Representa os tipos de combustível.
 * 
 * @author Andre Riffen
 * 
 */
public enum ETipoCombustivel {
    
    /**
     * Gasolina.
     */
    GASOLINA(1, "Gasolina"),
    
    /**
     * Etanol.
     */
    ETANOL(2, "Etanol"),
    
    /**
     * Flex.
     */
    FLEX(3, "Flex"),
    
    /**
     * Diesel.
     */
    DIESEL(4, "Diesel"),
    
    /**
     * GNV (Gás Natural Veicular).
     */
    GNV(5, "GNV"),
    
    /**
     * Outro tipo de combustível.
     */
    OUTRO(6, "Outro");
    
    private final int id;
    private final String descricao;

    /**
     * Construtor da enumeração ETipoCombustivel.
     *
     * @param id O ID do tipo de combustível.
     * @param descricao A descrição do tipo de combustível.
     */
    private ETipoCombustivel(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * Obtém o ID do tipo de combustível.
     *
     * @return O ID do tipo de combustível.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtém a descrição do tipo de combustível.
     *
     * @return A descrição do tipo de combustível.
     */
    public String getDescricao() {
        return descricao;
    }   

    /**
     * Retorna uma representação em string do tipo de combustível.
     *
     * @return Uma representação em string do tipo de combustível.
     */
    @Override
    public String toString() {
        return descricao;
    }
    
}
