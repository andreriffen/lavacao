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
import java.util.Date;

/**
 *
 * @author Riffen
 */
public class PessoaFisica extends Cliente {
    private String cpf;
    private LocalDate dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        sb.append("**** PESSOA FÍSICA ****").append("\n");
        sb.append("=============================================").append("\n");
        sb.append("Nome: " ).append(PessoaFisica.super.getNome()).append("\n");
        sb.append("Celular: " ).append(PessoaFisica.super.getCelular()).append("\n");
        sb.append("E-mail: " ).append(PessoaFisica.super.getEmail()).append("\n");
        sb.append("Data de Cadastro: " ).append(PessoaFisica.super.getDataCadastro()).append("\n");
        sb.append("CPF: " ).append(PessoaFisica.this.getCpf()).append("\n");
        sb.append("Data de Nascimento: " ).append(PessoaFisica.this.getDataNascimento()).append("\n");
        sb.append("=============================================").append("\n");
        return sb.toString();
    }

    @Override
    public String getDados(String observacoes) {
        StringBuilder sb = new StringBuilder();
        sb.append("**** PESSOA FÍSICA ****").append("\n");
        sb.append("=============================================").append("\n");
        sb.append("Nome: " ).append(PessoaFisica.super.getNome()).append("\n");
        sb.append("Celular: " ).append(PessoaFisica.super.getCelular()).append("\n");
        sb.append("E-mail: " ).append(PessoaFisica.super.getEmail()).append("\n");
        sb.append("Data de Cadastro: " ).append(PessoaFisica.super.getDataCadastro()).append("\n");
        sb.append("CPF: " ).append(PessoaFisica.this.getCpf()).append("\n");
        sb.append("Data de Nascimento: " ).append(PessoaFisica.this.getDataNascimento()).append("\n");
        sb.append("=============================================").append("\n");
        sb.append("Observações: ").append(observacoes).append("\n");
        return sb.toString();
    }  
}
