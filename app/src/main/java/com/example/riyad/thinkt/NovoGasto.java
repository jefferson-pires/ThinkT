package com.example.riyad.thinkt;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.List;

public class NovoGasto extends Activity{
    public static String data = "";

    private List<String> destino;
    private DAO dao;
    private  static  Button bt_data;
    private String destino_nome ="teste";
    private String tipo_nome ="teste";
    private Gasto gasto;
    private Viagem viagem;
    private static Double db_valor = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_gasto);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Novo Gasto");
        actionBar.setIcon(R.drawable.novo_gasto);
        //Funcao que ativa o botao up navagation
        actionBar.setDisplayHomeAsUpEnabled(true);
        dao = (DAO) getApplication();
        //Pega todos os nomes de todas as viagens
        destino = dao.nomesViagens();
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
        //Cria o segundo spinner(tipo de gasto)
        final Spinner tipos = (Spinner) findViewById(R.id.spinner2);
        //Cria um ArrayAdapter usando o array de string tipos_de_gastos
        ArrayAdapter<CharSequence> gastosAdapter = ArrayAdapter.createFromResource(this, R.array.tipos_de_gasto, android.R.layout.simple_spinner_item);
        //Especifica um layout para ser usado quando a lista de escolhas aparecer
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //Aplica o adaptador no spinner
        tipos.setAdapter(gastosAdapter);
        //Se selecionar algum tipo atualiza o valor da variavel tipo_nome
        tipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id){
                //atualiza o nome do tipo
                tipo_nome = tipos.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
        //Cria-se um botao
        bt_data = (Button) findViewById(R.id.bt_DatePicker);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Infla o menu com os botoes da action bar
        getMenuInflater().inflate(R.menu.menu_novo_gasto, menu);

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

    //DatePicker
    public void showDatePickerDialog(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
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
            //O botao assume o valor da data escolhida pelo usuario
            bt_data.setText(data);

        }
    }

    public void salvarGasto(View v){
        EditText ed_Valor = (EditText) findViewById(R.id.et_Valor);
        //Valida se o valor inserido eh um numero
        if(ed_Valor.getText().toString().equals("")){
            toast("Valor do gasto não defenido!");
        }else{
            db_valor = Double.parseDouble(ed_Valor.getText().toString());
            if(data.equals("")){
                toast("Data do gasto não defenida!");
            }else{
                gasto = new Gasto(db_valor, data, tipo_nome);
                //Salva o novo gasto na viagem especifica
                dao.busca((destino.indexOf((destino_nome))+1)).setGastos(gasto);
                toast("Gasto salvo com sucesso!");
            }
        }

    }

    private void toast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
