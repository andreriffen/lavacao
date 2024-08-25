package br.edu.ifsc.fln.model.domain;

public class Motor {
    
    private int potencia;
    private ETipoCombustivel combustivel;

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    } 

    public ETipoCombustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(ETipoCombustivel combustivel) {
        this.combustivel = combustivel;
    }
    
}
