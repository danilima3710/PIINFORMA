package com.example.daniel.pi;

import android.content.Context;

public class ArtigoController {
    Context mcontext;
    ArtigoDAO dao;
    public ArtigoController(Context c) {
        mcontext = c;
        dao = new ArtigoDAO(c);
    }

    public void inserir(Artigo artigo) {
        if (!artigo.titulo.trim().equals("")) {
            dao.inserir(artigo);
        }
    }

    public void alterar(Artigo artigo){
        dao.atualizar(artigo);
    }
}
