package com.example.riyad.thinkt;

import android.app.SearchManager;
import android.content.Intent;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
public final String data_agora = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Pega a action bar
        ActionBar actionBar = getActionBar();
        //Define um nome a ser exebido na action bar
        actionBar.setTitle("Postal");
        //Define uma logo a ser exebido na action bar
        actionBar.setIcon(R.drawable.ic_logo);
        //Ativa a exibicao do nome na action bar
        actionBar.setDisplayShowTitleEnabled(true);
        //Ativa a exibicao do icone na action bar
        actionBar.setDisplayShowHomeEnabled(true);
        //Desativa a opcao up navagation
        actionBar.setDisplayHomeAsUpEnabled(false);

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

        if(id == R.id.action_extras){
            Intent intent = new Intent(this, Extras.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    public void novaViagem(View view){
        Intent intent = new Intent(this, NovaViagem.class);
        startActivity(intent);
    }

    public void novoGasto(View view){
        Intent intent = new Intent(this, NovoGasto.class);
        startActivity(intent);
    }

    public void minhasViagens(View view){
        Intent intent = new Intent(this, MinhasViagens.class);
        startActivity(intent);
    }

    public void meuPerfil(View view){

        Intent intent = new Intent(this, MeuPerfil.class);
        startActivity(intent);
    }

}
