package com.example.riyad.thinkt;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MinhasViagens extends Activity implements AdapterView.OnItemClickListener {
    private ListView listview ;
    ArrayList<Viagem> viagens ;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_viagens);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Minhas viagens");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setIcon(R.drawable.minhas_viagens);
        //Funcao que ativa o botao up navagation
        actionBar.setDisplayHomeAsUpEnabled(true);

        DAO application = (DAO) getApplication();
        viagens = application.listarTodas();
        listview = (ListView)findViewById(R.id.minhasViagensList);
        listview.setAdapter(new ViagensAdapter(this,viagens));
        listview.setOnItemClickListener(this);

    }

    // ao clicar em um itém da lista de Viagens vai abrir outra tela com os gastos daquela viagem
    public void onItemClick(AdapterView<?> parent, View view,int idx, long id){
        int item = (int) listview.getItemIdAtPosition(idx);
        Viagem v = this.viagens.get(item);
        Intent intent = new Intent(this,MeusGastos.class);
        //pega o id da viagem que recebeu o click
        intent.putExtra("Valor",v.getId());
        startActivity(intent);
        Toast.makeText(this, "Local: " + v.getLocalViagem(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Infla o menu com os botoes da action bar
        getMenuInflater().inflate(R.menu.menu_minhas_viagens, menu);

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
