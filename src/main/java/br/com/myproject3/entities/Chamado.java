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
public class Chamado {
    private int id;
    private String titulo;
    private String descricao;
    private Date data_hora_abertura;
    private int id_responsavel;
    private String status;
    private String nome_cliente;

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_hora_abertura() {
        return data_hora_abertura;
    }

    public void setData_hora_abertura(Date data_hora_abertura) {
        this.data_hora_abertura = data_hora_abertura;
    }

    public int getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(int id_responsavel) {
        this.id_responsavel = id_responsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
