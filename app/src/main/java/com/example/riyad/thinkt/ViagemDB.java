package com.example.riyad.thinkt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefferson on 31/08/2016.
 */
public class ViagemDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    //Nome do banco
    public static final String NOME_BANCO = "postbase.sqlite";
    //Versao do banco
    private static final int VERSAO_BANCO = 1;

    public ViagemDB(Context context){
        //context, nome do banco, factory, versao
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabela viagem...");
        db.execSQL("create table if not exists viagem(_id integer primary key " +
                "autoincrement, icone int, localViagem text, data text);");
        Log.d(TAG, "Tabela criada com sucesso");

        Log.d(TAG, "Criando a Tabela gasto...");
        db.execSQL("create table if not exists gasto(_id integer primary key autoincrement, " +
                "constraint`idViagem` foreign key ( `_id` ) references `viagem` ( `_id` )" +
                ", valor double, data text, tipo text;");
        Log.d(TAG, "Tabela criada com sucesso");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long save(Viagem viagem){
        long id = viagem.getId();
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("icone", viagem.getIcone());
            values.put("data", viagem.getData());
            values.put("localViagem", viagem.getLocalViagem());

            ContentValues values2 = new ContentValues();

            if(id != 0){
                String _id = String.valueOf(viagem.getId());
                String[]whereArgs = new String[]{_id};

                int count = db.update("viagem", values, "_id=?", whereArgs);
                Log.d(TAG, "Post atualizado com sucesso");

                return count;
            }else{
                //insert into post values(...)
                id = db.insert("viagem", "", values);
                Log.d(TAG, "Viagem criado com sucesso");
                return id;
            }
        }finally {
            db.close();
        }
    }

    public long saveGasto(Gasto gasto){
        long id = gasto.getId() ;
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("valor", gasto.getValor());
            values.put("tipo", gasto.getTipo());
            values.put("idViagem", gasto.getId());
            values.put("data", gasto.getData());

            if(id != 0){
                String _id = String.valueOf(gasto.getId());
                String[]whereArgs = new String[]{_id};

                int count = db.update("gasto", values, "_id=?", whereArgs);
                Log.d(TAG, "Gasto atualizado com sucesso");

                return count;
            }else{
                //insert into post values(...)
                id = db.insert("gasto", "", values);
                Log.d(TAG, "Gasto adicionado com sucesso");
                return id;
            }
        }finally {
            db.close();
        }
    }

    public ArrayList<Gasto> buscarGastosPorViagem(int idViagem){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query("gasto", null, "idViagem = ?", new String[]{String.valueOf(idViagem)},null, null, null);
            c.moveToFirst();
            ArrayList<Gasto> gastos = new ArrayList<Gasto>();
            for(int i = 0; i <= c.getCount();i++){
                Gasto gasto = new Gasto();
                gasto.setData(c.getString(c.getColumnIndex("data")));
                gasto.setId(c.getInt(c.getColumnIndex("_id")));
                gasto.setIdViagem(c.getInt(c.getColumnIndex("idViagem")));
                gasto.setValor(c.getDouble(c.getColumnIndex("valor")));
                gasto.setTipo(c.getString(c.getColumnIndex("tipo")));

                gastos.add(gasto);
            }
            Log.d(TAG, "Consultou viagem.");
            return gastos;
        } catch (Exception e) {
            Log.d(TAG, "Erro: " + e.getMessage());
            return null;
        } finally {
            if (c != null) {
                c.close();
            }
            db.close();
        }
    }

    public int delete(Viagem viagem){
        SQLiteDatabase db = getWritableDatabase();
        try{
            int count = db.delete("viagem", "_id=?", new String[]{String.valueOf(viagem.getId())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        }finally {
            db.close();
        }
    }

    public ArrayList<Viagem> findAll(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            //Select * from post
            Cursor c = db.query("viagem", null, null, null, null, null, null, null);
            return toList(c);
        }finally {
            db.close();
        }
    }

    private ArrayList<Viagem> toList(Cursor c){
        ArrayList<Viagem> viagens = new ArrayList<Viagem>();
        if(c.moveToFirst()){
            do{
                Viagem viagem = new Viagem();
                //Recupera os atributos de viagem
                viagem.setId(c.getInt(c.getColumnIndex("_id")));
                viagem.setData(c.getString(c.getColumnIndex("data")));
                viagem.setLocalViagem(c.getString(c.getColumnIndex("localViagem")));
                viagem.setIcone(c.getInt(c.getColumnIndex("icone")));
                viagens.add(viagem);
            } while(c.moveToNext());
        }

        return viagens;
    }

    //Executa um SQL
    public void execSQL(String sql){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL(sql);
        }finally {
            db.close();
        }
    }

    public Viagem findById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query("viagem", null, "_id = ?", new String[]{String.valueOf(id)},
                    null, null, null);
            c.moveToFirst();
            Viagem viagem = new Viagem();
            viagem.setId(c.getInt(c.getColumnIndex("_id")));
            viagem.setData(c.getString(c.getColumnIndex("data")));
            viagem.setIcone(c.getInt(c.getColumnIndex("icone")));
            viagem.setLocalViagem(c.getString(c.getColumnIndex("localViagem")));
            Log.d(TAG, "Consultou viagem.");
            return viagem;
        } catch (Exception e) {
            Log.d(TAG, "Erro: " + e.getMessage());
            return null;
        } finally {
            if (c != null) {
                c.close();
            }
            db.close();
        }
    }
}
