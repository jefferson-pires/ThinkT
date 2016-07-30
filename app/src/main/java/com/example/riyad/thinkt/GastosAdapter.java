package com.example.riyad.thinkt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefferson on 29/07/2016.
 */
public class GastosAdapter extends BaseAdapter {
    private final Context context;
    private final List<Gasto> gastos;

    public GastosAdapter (Context context, ArrayList<Gasto> gastos) {
        this.context = context;
        this.gastos = gastos;
    }

    @Override
    public int getCount() {
        return gastos != null ? gastos.size():0;
    }

    @Override
    public Object getItem(int position) {
        return gastos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_gastos,parent,false);
        TextView valorGasto = (TextView) view.findViewById(R.id.valorGasto);
        TextView dataGasto = (TextView) view.findViewById(R.id.dataGasto);
        TextView tipoDeGasto = (TextView) view.findViewById(R.id.tipoGasto);

        Gasto gasto = gastos.get(position);

        // Converter o totalGastos em String
        String totalGastosString = String.valueOf(gasto.getValor());
        // Retira o ponto e coloca virgula
        String gastoValorSemPonto = totalGastosString.replace(".",",");

        valorGasto.setText(gastoValorSemPonto);
        dataGasto.setText(gasto.getData());
        tipoDeGasto.setText(gasto.getTipo());

        return view;
    }
}
