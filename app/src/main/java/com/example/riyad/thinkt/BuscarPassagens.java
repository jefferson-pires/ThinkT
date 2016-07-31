package com.example.riyad.thinkt;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BuscarPassagens extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_passagens);
        //Pega a action bar
        ActionBar actionBar = getActionBar();
        //Define um nome a ser exebido na action bar
        actionBar.setTitle("Buscar Passagens");
        //Define uma logo a ser exebido na action bar
        actionBar.setIcon(R.drawable.ic_add_location);
        //Ativa a exibicao do nome na action bar
        actionBar.setDisplayShowTitleEnabled(true);
        //Ativa a exibicao do icone na action bar
        actionBar.setDisplayShowHomeEnabled(true);
        //Ativa a opcao up navagation
        actionBar.setDisplayHomeAsUpEnabled(true);
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
