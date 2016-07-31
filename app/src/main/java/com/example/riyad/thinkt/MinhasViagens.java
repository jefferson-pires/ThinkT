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
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.ArrayList;

public class MinhasViagens extends Activity implements AdapterView.OnItemClickListener {
    private ListView listview ;
    ArrayList<Viagem> viagens;
    ArrayList<Viagem> busca;

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
        busca = new ArrayList<>();

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
        //SearchView
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());
        //ActionShare
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider share = (ShareActionProvider) shareItem.getActionProvider();
        Intent intent = new Intent(Intent.ACTION_SEND);
        share.setShareIntent(getDefaultIntent());

        return true;
    }
    //Metodo de tratamento para cada item selecionado na action bar
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

    //Metodo de tratamento do searchview
    private SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                //Usuario fez a busca
                int id;
                for (Viagem v: viagens) {
                    if(v.getLocalViagem().equals(query)){
                        id = v.getId();
                        busca.add(v);
                        break;
                    }
                }


                listview.setAdapter(new ViagensAdapter(MinhasViagens.this,busca));
                listview.setOnItemClickListener(MinhasViagens.this);
                listview.refreshDrawableState();
                toast("Resultado da busca para " + query);
                return  false;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                //Mudou o texto digitado
                return false;
            }
        };
    }

    //Intent que define o conteudo compartilhado
    private Intent getDefaultIntent(){
        String texto = "";
        for (Viagem v: viagens) {
            texto = texto + v.getLocalViagem() + "\n" + v.getData() + "\n" + v.totalGastos() + "\n\n";
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, texto);
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

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
