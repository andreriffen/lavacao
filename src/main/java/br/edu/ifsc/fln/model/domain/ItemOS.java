package br.edu.ifsc.fln.model.domain;

/**
 * Representa um item na Ordem de Serviço (OS).
 *
 * Cada item está relacionado a um serviço e pode ter um valor de serviço específico e observações.
 *
 * @author andreriffen
 *
 */
public class ItemOS {
    private double valorServico;  // Valor do serviço aplicado no item da OS
    private String observacoes;   // Observações adicionais sobre o item
    private Servico servico;      // Serviço associado ao item

    // Construtor, getters e setters

    public ItemOS() {
    }

    public ItemOS(String observacoes, double valorServico, Servico servico) {
        this.observacoes = observacoes;
        this.valorServico = valorServico;
        this.servico = servico;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
