package com.example.riyad.thinkt;
import android.support.v4.app.Fragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.Calendar;

public class NovaViagem extends Activity {
    public static String destino = "";
    public static String tipo = "";
    public static String data = "";

    private Viagem viagem ;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_viagem);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Nova Viagem");
        actionBar.setIcon(R.drawable.nova_viagem);
        //Funcao que ativa o botao up navagation
        actionBar.setDisplayHomeAsUpEnabled(true);
        viagem = new Viagem();
        dao = (DAO) getApplication();


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

    //Metodo para tratar os radiobutton(tipo de viagem)
    public void onRadioButtonClicked(View view){
        //Qual o botao marcado agora?
        boolean checked = ((RadioButton) view).isChecked();

        //Checa qual radio button foi clicado
        switch (view.getId()){
            case R.id.rb_Lazer:
                if (checked)
                    toast("Tipo da viagem defenido como lazer");
                    tipo="Lazer";
                    viagem.setIcone(R.drawable.lazer);
                break;
            case R.id.rb_Negocios:
                if(checked)
                    toast("Tipo da viagem defenido como negocios");
                    tipo = "Negocios";
                    viagem.setIcone(R.drawable.negocios);
                break;
        }
    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstance){
            //Usa a data atual como a data default
            final Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            //Cria uma nova instancia do DatePickerDialog e a retorna
            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        public void onDateSet(DatePicker view, int ano, int mes, int dia ){
            //Faz alguma coisa com a data escolhida pelo usuario
            data = (dia + "/" + (mes+1) + "/" + ano);

        }
    }


    //DatePicker
    public void showDatePickerDialog(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    //Metodo para gravar a viagem
    public void gravarViagem(View view){
        EditText et_Destino = (EditText) findViewById(R.id.et_Destino);
        destino = et_Destino.getText().toString();
        viagem.setLocalViagem(destino);
        viagem.setData(data);
        dao.adiciona(viagem);
        toast("viagem gravada com sucesso!\nDestino: " + destino + "\nTipo: " + tipo + "\ndata: " + data);
    }


}
