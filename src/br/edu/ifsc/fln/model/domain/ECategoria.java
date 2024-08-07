package br.edu.ifsc.fln.model.domain;

/**
 *
 * Representa as categorias de veículos.
 * 
 * @author Andre Riffen
 * 
 */
public enum ECategoria {

    /**
     * Categoria Pequeno.
     */
    PEQUENO(1, "Pequeno"),

    /**
     * Categoria Médio.
     */
    MEDIO(2, "Medio"),

    /**
     * Categoria Grande.
     */
    GRANDE(3, "Grande"),

    /**
     * Categoria Moto.
     */
    MOTO(4, "Moto"),

    /**
     * Categoria Padrão.
     */
    PADRAO(5, "Padrao");

    private final int id;
    private final String descricao;

    /**
     * Construtor da enumeração ECategoria.
     *
     * @param id O ID da categoria.
     * @param descricao A descrição da categoria.
     */
    private ECategoria(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * Obtém o ID da categoria.
     *
     * @return O ID da categoria.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtém a descrição da categoria.
     *
     * @return A descrição da categoria.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna uma representação em string da enumeração ECategoria.
     *
     * @return Uma representação em string da enumeração ECategoria.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ECategoria{");
        sb.append("ordinal=").append(ordinal());
        sb.append(", name=").append(name());
        sb.append(", id=").append(id);
        sb.append(", descricao=").append(descricao);
        sb.append('}');
        return sb.toString();
    }

}
