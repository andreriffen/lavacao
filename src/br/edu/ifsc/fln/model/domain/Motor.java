/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.domain;

/**
 * 
 * Representa o motor de um veículo.
 * 
 * @author Andre Riffen
 * 
 */
public class Motor {
    
    private int potencia;
    private ETipoCombustivel combustivel;

    /**
     * Obtém a potência do motor.
     *
     * @return A potência do motor.
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Define a potência do motor.
     *
     * @param potencia A potência do motor.
     */
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    } 

    /**
     * Obtém o tipo de combustível do motor.
     *
     * @return O tipo de combustível do motor.
     */
    public ETipoCombustivel getCombustivel() {
        return combustivel;
    }

    /**
     * Define o tipo de combustível do motor.
     *
     * @param combustivel O tipo de combustível do motor.
     */
    public void setCombustivel(ETipoCombustivel combustivel) {
        this.combustivel = combustivel;
    }    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Motor{");
        sb.append("potencia=").append(potencia);
        sb.append(", combustivel=").append(combustivel);
        sb.append('}');
        return sb.toString();
    }
    
}
