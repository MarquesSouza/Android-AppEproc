package com.example.marquesdesouza.appeproc.model;

/**
 * Created by Marques de Souza on 02/01/2017.
 */

public class Assunto {
    private int codigo;
    private String descricao;
    private String principal;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
