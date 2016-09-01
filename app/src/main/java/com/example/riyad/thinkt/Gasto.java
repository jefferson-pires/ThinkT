package com.example.riyad.thinkt;

/**
 * Created by Jefferson on 26/07/2016.
 */
public class Gasto {
    private int id ;
    private double valor;
    private String data;
    private String tipo;
    private int idViagem;

    Gasto (double valor, String data, String tipo,int idViagem){
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
        this.idViagem = idViagem;
    }

    Gasto(){

    }

    public Gasto setId(int id) {
        this.id = id;
        return this;
    }

    public Gasto setValor(double valor) {
        this.valor = valor;
        return this;
    }

    public Gasto setData(String data) {
        this.data = data;
        return this;
    }

    public Gasto setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Gasto setIdViagem(int idViagem) {
        this.idViagem = idViagem;
        return this;
    }

    public String getData(){
        return this.data;
    }

    public int getId(){
        return this.id;
    }

    public int getIdViagem(){
        return this.idViagem;
    }

    public Double getValor(){
        return this.valor;
    }

    public String getTipo(){
        return this.tipo;
    }

}
