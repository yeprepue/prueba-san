package com.example.prueba_san.domain.models;

public class Cliente {
    
    private Long id;
    private String nombre;
    private String email;
    private String segmento;
    private Bolean activo; 
    

    public Cliente (){

    }

    public Cliente (Long, String, String, String, Bolean){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.segmento = segmento;
        this.activo = activo;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setSegmento(String segmento){
        this.segmento = segmento;
    }
    public String getSegmento(){
        return segmento;
    }
    public void setActivo(Bolean activo){
        this.activo = activo;
    }
    public Bolean getActivo(){
        return activo;
    }
}
