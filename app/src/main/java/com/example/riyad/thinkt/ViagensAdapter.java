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
 * Created by Jefferson on 26/07/2016.
 */
public class ViagensAdapter extends BaseAdapter {
    private final Context context;
    private final List<Viagem> viagens;

    public ViagensAdapter(Context context, ArrayList<Viagem> viagens) {
        this.context = context;
        this.viagens = viagens;
    }

    @Override
    public int getCount() {
        return viagens != null ? viagens.size():0;
    }

    @Override
    public Object getItem(int position) {
        return viagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_viagens,parent,false);
        TextView localViagem = (TextView) view.findViewById(R.id.localViagem);
        ImageView img = (ImageView) view.findViewById(R.id.imagemTipoViagem);
        Viagem viagem = viagens.get(position);
        localViagem.setText(viagem.getLocalViagem());
        img.setImageResource(viagem.getIcone());
        return view;
    }
}
