package com.example.riyad.thinkt;

/**
 * Created by Jefferson on 26/07/2016.
 */
public class Gasto {
    private double valor;
    private String data;
    private String tipo;

    Gasto (double valor, String data, String tipo){
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getData(){
        return this.data;
    }

    public Double getValor(){
        return this.valor;
    }

    public String getTipo(){
        return this.tipo;
    }

}
