package com.example.riyad.thinkt;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MeusGastos extends Activity {
    private ListView listview ;
    ArrayList<Gasto> gastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_gastos);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Meus Gastos");
        actionBar.setIcon(R.drawable.novo_gasto);
        //Funcao que ativa o botao up navagation
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        //pega o id da viagem que recebeu o click
        int idViagem = intent.getIntExtra("Valor", 0);

        ViagemDB db = new ViagemDB(this);
        gastos = db.buscarGastosPorViagem(idViagem);
        listview = (ListView)findViewById(R.id.gastosList);
        listview.setAdapter(new GastosAdapter(this,gastos));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Infla o menu com os botoes da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    //Metodo de tratamento da upnavagation, caso queira, pode alterar o manifesto para fazer a mesma coisa
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        //Clicou no "up navagation"
        if(item.getItemId() == android.R.id.home){
            //O metodo finish() vai encerrar essa activity
            finish();
            return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
}
