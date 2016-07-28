package com.example.riyad.thinkt;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jefferson on 26/07/2016.
 */
public class DAO extends Application{
    private static Map<Integer, Viagem> banco = new HashMap<Integer, Viagem>();
    private static AtomicLong contador = new AtomicLong(3);

    static {
        ArrayList<Gasto> gastos = new ArrayList<Gasto>();
        Gasto gasto1 = new Gasto(20.00,"27/06/2016","transporte");
        Gasto gasto2 = new Gasto(200.00,"27/06/2016","hospedagem");
        gastos.add(gasto1);
        gastos.add(gasto2);

        Viagem viagem1 = new Viagem(R.drawable.lazer,"lençois maranhenses","22/05/2016",gastos);
        viagem1.setId(1);

        ArrayList<Gasto> gastos2 = new ArrayList<Gasto>();
        Gasto gasto3 = new Gasto(40.00,"27/06/2016","transporte");
        Gasto gasto4 = new Gasto(150.00,"27/06/2016","alimentação");
        gastos2.add(gasto3);
        gastos2.add(gasto4);

        Viagem viagem2 = new Viagem(R.drawable.negocios,"Miami","22/05/2016",gastos2);
        viagem2.setId(2);

        ArrayList<Gasto> gastos3 = new ArrayList<Gasto>();
        Gasto gasto5 = new Gasto(40.00,"27/06/2016","transporte");
        Gasto gasto6 = new Gasto(150.00,"27/06/2016","alimentação");
        gastos3.add(gasto5);
        gastos3.add(gasto6);

        Viagem viagem3 = new Viagem(R.drawable.negocios,"Piaui","12/06/2016",gastos2);
        viagem2.setId(3);

        banco.put(1,viagem1);
        banco.put(2,viagem2);
        banco.put(3,viagem3);
    }

    public void adiciona(Viagem viagem) {
        int id = (int) contador.incrementAndGet();
        viagem.setId(id);
        banco.put(id, viagem);
    }

    public Viagem busca(Long id) {
        return banco.get(id);
    }

    public ArrayList<Viagem> listarTodas(){
        ArrayList<Viagem> viagens = new ArrayList<Viagem>();
        for (int i = 1; i <= banco.size() ; i++) {
            viagens.add(banco.get(i));
        }
        return viagens;
    }

    public Viagem remove(long id) {
        return banco.remove(id);
    }
}
