package com.example.daniel.pi;

import android.content.Intent;

public class Artigo {
    String id_artigo;
    String titulo;
    String descricao;
    String categoria;
    String publico;
    String tipoArtigo;
    String resumo;

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    String url;

    public String getId_artigo() {
        return id_artigo;
    }

    public void setId_artigo(String id_artigo) {
        this.id_artigo = id_artigo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitulo() {
        this.titulo = titulo;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public String getTipoArtigo() {
        return tipoArtigo;
    }

    public void setTipoArtigo(String tipoArtigo) {
        this.tipoArtigo = tipoArtigo;
    }

    @Override
    public String toString() {
        return "Artigo{" +
                "id_artigo=" + id_artigo +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", publico='" + publico + '\'' +
                ", tipoArtigo='" + tipoArtigo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }



}
