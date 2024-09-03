/*
 * The MIT License
 *
 * Copyright 2024 Riffen.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.edu.ifsc.fln.model.domain;

import java.time.LocalDate;

/**
 *
 * @author Riffen
 */
public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String inscricaoEstadual;
    private LocalDate dataNascimento;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("**** PESSOA JURÍDICA ****").append("\n");
        sb.append("=============================================").append("\n");
        sb.append("Nome: " ).append(PessoaJuridica.super.getNome()).append("\n");
        sb.append("Celular: " ).append(PessoaJuridica.super.getCelular()).append("\n");
        sb.append("E-mail: " ).append(PessoaJuridica.super.getEmail()).append("\n");
        sb.append("Data de Cadastro: " ).append(PessoaJuridica.super.getDataCadastro()).append("\n");
        sb.append("CNPJ: " ).append(PessoaJuridica.this.getCnpj()).append("\n");
        sb.append("Data de Nascimento: " ).append(PessoaJuridica.this.getDataNascimento()).append("\n");
        sb.append("Inscrição Estadual: " ).append(PessoaJuridica.this.getInscricaoEstadual()).append("\n");
        sb.append("=============================================").append("\n");
        return sb.toString();
    }

    @Override
    public String getDados(String observacoes) {
        StringBuilder sb = new StringBuilder();
        sb.append("**** PESSOA JURÍDICA ****").append("\n");
        sb.append("=============================================").append("\n");
        sb.append("Nome: " ).append(PessoaJuridica.super.getNome()).append("\n");
        sb.append("Celular: " ).append(PessoaJuridica.super.getCelular()).append("\n");
        sb.append("E-mail: " ).append(PessoaJuridica.super.getEmail()).append("\n");
        sb.append("Data de Cadastro: " ).append(PessoaJuridica.super.getDataCadastro()).append("\n");
        sb.append("CNPJ: " ).append(PessoaJuridica.this.getCnpj()).append("\n");
        sb.append("Data de Nascimento: " ).append(PessoaJuridica.this.getDataNascimento()).append("\n");
        sb.append("Inscrição Estadual: " ).append(PessoaJuridica.this.getInscricaoEstadual()).append("\n");
        sb.append("=============================================").append("\n");
        sb.append("Observações: ").append(observacoes).append("\n");
        return sb.toString();
    } 
    
}
