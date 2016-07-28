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
        actionBar.setTitle("Think");
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
        //Searchview

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());

        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider share = (ShareActionProvider) shareItem.getActionProvider();
        Intent intent = new Intent(Intent.ACTION_SEND);
        share.setShareIntent(getDefaultIntent());


        return true;
    }

    private SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                //Usuario fez a busca
                toast("Buscar o texto: " + query);
                return  false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                //Mudou o texto digitado
                return false;
            }
        };
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

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //Intent que define o conteudo compartilhado
    private Intent getDefaultIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, "Texto a se compartilhar!");
        return intent;
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

    public void selecionarOpcao(){
        toast("voce selecionou: ");
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
