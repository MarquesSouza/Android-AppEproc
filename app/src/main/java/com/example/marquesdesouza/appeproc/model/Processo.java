package com.example.marquesdesouza.appeproc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Marques de Souza on 02/01/2017.
 */


public class Processo {
    private Integer numeroProcesso;
    private Date data;
    private String juiz;
    private String comarca;
    private String tipoProcesso;
    private String OrgaoJugador;
    private String ClasseAcao;
    private String situacao;
    private List<Assunto> assuntos;
    private List<InformacoesAdicionais> informacoesAdicionaises;
   // private List<Processo> processosRelacionados;
    private List<Parte> partes;
    private List<Evento> eventos;

    public String getOrgaoJugador() {
        return OrgaoJugador;
    }

    public void setOrgaoJugador(String orgaoJugador) {
        OrgaoJugador = orgaoJugador;
    }

    public String getClasseAcao() {
        return ClasseAcao;
    }

    public void setClasseAcao(String classeAcao) {
        ClasseAcao = classeAcao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<Assunto> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<Assunto> assuntos) {
        this.assuntos = assuntos;
    }

    public List<InformacoesAdicionais> getInformacoesAdicionaises() {
        return informacoesAdicionaises;
    }

    public void setInformacoesAdicionaises(List<InformacoesAdicionais> informacoesAdicionaises) {
        this.informacoesAdicionaises = informacoesAdicionaises;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Integer getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(Integer numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getJuiz() {
        return juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getTipoProcesso() {
        return tipoProcesso;
    }

    public void setTipoProcesso(String tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }

    public List<Parte> getPartes() {
        return partes;
    }

    public void setPartes(List<Parte> partes) {
        this.partes = partes;
    }
}
