package br.edu.ifsc.fln.model.domain;

/**
 * Representa um serviço disponível na Ordem de Serviço.
 * Cada serviço possui uma descrição, valor e pontos de fidelidade.
 *
 * @author andreriffen
 *
 */
public class Servico {
    private int id;             // Identificador único do serviço
    private String descricao;   // Descrição do serviço
    private double valor;       // Valor do serviço
    private final int pontos;         // Pontos de fidelidade do serviço
    private ECategoria categoria; // Categoria do veículo

    // Construtor, getters e setters

    public Servico() {
        this.pontos = 10; // Pontos fixos para todos os serviços
    }

    public Servico(int id, String descricao, double valor, ECategoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.pontos = 10; // Pontos fixos para todos os serviços
        definirValor();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontos() {
        return pontos;
    }



    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {

        definirValor();
        this.categoria = categoria;
    }

    // Metodo para definir o valor baseado na categoria
    private void definirValor() {
        switch (categoria) {
            case PADRAO:
                this.valor = 75.0;
                break;
            case PEQUENO:
                this.valor = 50.0;
                break;
            case MEDIO:
                this.valor = 100.0;
                break;
            case GRANDE:
                this.valor = 125.0;
                break;
            case MOTO:
                this.valor = 80.0;
                break;
            case CAMINHAO:
                this.valor = 150.0;
                break;
        }
    }
}
