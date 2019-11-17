package com.example.daniel.pi;

public class upload {
    private String wNome;
    private String wUrl;

    public String getwNome() {
        return wNome;
    }

    public void setwNome(String wNome) {
        this.wNome = wNome;
    }

    public String getwUrl() {
        return wUrl;
    }

    public void setwUrl(String wUrl) {
        this.wUrl = wUrl;
    }

    public upload(){
        //Contructor vazio
    }

    public upload(String nome, String url){
        if (nome.trim().equals("")){
            nome = "Sem Nome";
        }

        wNome = nome;
        wUrl = url;
    }
}
