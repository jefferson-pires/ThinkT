package com.example.riyad.thinkt;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jefferson on 26/07/2016.
 */
public class Viagem {
    private int icone;
    private String localViagem;
    private String data;
    private int id;
    private ArrayList<Gasto> gastos = new ArrayList<Gasto>();

    Viagem(){

    }

    Viagem(int icone, String localViagem, String data, ArrayList<Gasto> gastos){
        this.icone = icone ;
        this.gastos = gastos;
        this.data = data;
        this.localViagem = localViagem ;
    }

    public void setId(int id){
        this.id = id ;
    }

    public int getId(){
        return this.id;
    }

    public int getIcone() {
        return icone;
    }

    public String getLocalViagem() {
        return localViagem;
    }

    public ArrayList<Gasto> getGastos() {
        return gastos;
    }

    public String getData() {
        return data;
    }

    public void setGastos(Gasto gasto){
        this.gastos.add(gasto);
    }

    public double totalGastos(){
        double total = 0;
        for (Gasto gastos: this.gastos) {
            total = total + gastos.getValor();
        }
        return total ;
    }

    public Viagem setIcone(int icone) {
        this.icone = icone;
        return this;
    }

    public void setLocalViagem (String localViagem) {
        this.localViagem = localViagem;
    }

    public Viagem setData(String data) {
        this.data = data;
        return this;
    }

}
