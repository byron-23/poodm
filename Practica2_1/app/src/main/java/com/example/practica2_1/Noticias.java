package com.example.practica2_1;

public class Noticias {
    private String titulo;
    private String subtitulo;
    public Noticias(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getSubtitulo(){
        return subtitulo;
    }
}
