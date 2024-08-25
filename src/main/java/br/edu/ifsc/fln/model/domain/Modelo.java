package br.edu.ifsc.fln.model.domain;

/**
 *
 * Representa um modelo de ve�culo.
 * 
 * @author andreriffen
 * 
 */
public class Modelo {
    
    private int id;
    private String descricao;
    
    private Marca marca;
    private Motor motor;  
    private ECategoria categoria = ECategoria.PADRAO;//Verificar!!!

    /**
     * Metodo Inicializador do motor.
     */
    private void defineMotor() {
        motor = new Motor();
    }

    /**
     * Construtor padrão que inicializa o motor.
     */
    public Modelo() {
        defineMotor();
    }

    /**
     * Construtor que inicializa a descriçãoo e a marca do modelo.
     *
     * @param descricao A descrição do modelo.
     * @param marca A marca do modelo.
     */
    public Modelo(String descricao, Marca marca) {
        this.descricao = descricao;
        this.marca = marca;
        defineMotor();
    }

    /**
     * Obtém o ID do modelo.
     *
     * @return O ID do modelo.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do modelo.
     *
     * @param id O ID do modelo.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém a descrição do modelo.
     *
     * @return A descrição do modelo.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do modelo.
     *
     * @param descricao A descri��o do modelo.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém a marca do modelo.
     *
     * @return A marca do modelo.
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * Define a marca do modelo.
     *
     * @param marca A marca do modelo.
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }  
    
    /**
     * Obtém o motor do modelo.
     *
     * @return O motor do modelo.
     */
    public Motor getMotor() {
        return motor;
    }

    /**
     * Define o motor do modelo.
     *
     * @param motor O motor do modelo.
     */
    public void setMotor(Motor motor) {
        this.motor = motor;
    }
    
    /**
     * Obtém a categoria do modelo.
     *
     * @return A categoria do modelo.
     */
    public ECategoria getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do modelo.
     *
     * @param categoria A categoria do modelo.
     */
    public void setCategoria(ECategoria categoria) {
        this.categoria = categoria;
    }

}