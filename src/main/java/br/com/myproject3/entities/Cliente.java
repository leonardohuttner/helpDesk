/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myproject3.entities;

import java.util.Date;
/**
 *
 * @author leonardohuttner
 */
public class Cliente extends Pessoa {
    private String endereco;
    private String cpf;
    private Date clienteDesde;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getClienteDesde() {
        return clienteDesde;
    }

    public void setClienteDesde(Date clienteDesde) {
        Date date = new Date();
        this.clienteDesde = date;
    }
    
    
}
