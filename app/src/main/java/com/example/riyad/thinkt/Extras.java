package com.example.riyad.thinkt;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class Extras extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
        //Pega a action bar
        ActionBar actionBar = getActionBar();
        //Define um nome a ser exebido na action bar
        actionBar.setTitle("Extras");
        //Define uma logo a ser exebido na action bar
        actionBar.setIcon(R.drawable.ic_add);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_search){
            toast("Clicou no Search!");
            return true;
        }else if(id == R.id.action_refresh){
            toast("Clicou no refresh!");
            return true;
        }else if(id == R.id.action_settings){
            toast("Clicou no settings");
            return true;
        } else if(id == R.id.action_share){
            toast("Clicou no share!");
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    //Metodo de tratamento para exibir fotos
    public void exibirFotos(View v){
        Intent intent = new Intent(this, BuscarFotos.class);
        startActivity(intent);
    }

    //Metodo de tratamento para procurar passagens
    public void buscarPassagem(View v){
        Intent intent = new Intent(this, BuscarPassagens.class);
        startActivity(intent);
    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
