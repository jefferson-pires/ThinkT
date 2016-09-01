package com.example.riyad.thinkt;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class BuscarFotos extends Activity {
    private ArrayList<String> destino;
    private ViagemDB dao;
    private String destino_nome ="teste";
    ArrayList<Viagem> viagens ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_fotos);
        //Pega a action bar
        ActionBar actionBar = getActionBar();
        //Define um nome a ser exebido na action bar
        actionBar.setTitle("Buscar Fotos");
        //Define uma logo a ser exebido na action bar
        actionBar.setIcon(R.drawable.ic_camera);
        //Ativa a exibicao do nome na action bar
        actionBar.setDisplayShowTitleEnabled(true);
        //Ativa a exibicao do icone na action bar
        actionBar.setDisplayShowHomeEnabled(true);
        //Ativa a opcao up navagation
        actionBar.setDisplayHomeAsUpEnabled(true);
        dao = new ViagemDB(this);
        //Pega todos os nomes de todas as viagens
        viagens = dao.findAll();
        destino = new ArrayList<String>();
        for (Viagem viagem:viagens) {
            destino.add(viagem.getLocalViagem());
        }
        //Cria o primeiro spinner (viagens)
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //Cria um ArrayAdapter usando um layout de spinner padrao
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, destino);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        //Se selecionar algum destino atualiza a variavel nome_destino
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id){
                //Atualiza o nome
                destino_nome = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
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

    //Metodo de tratamento do evento clicar no botao
    public void buscarFotos(View view){
        String site = "https://www.google.com.br/search?hl=pt-BR&site=imghp&tbm=isch&source=hp&biw=1366&bih=599&q=cidade+" + destino_nome;
        Uri uri = Uri.parse(site);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
