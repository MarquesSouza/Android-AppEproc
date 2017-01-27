package com.example.marquesdesouza.appeproc.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.marquesdesouza.appeproc.model.Processo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marques de Souza on 03/01/2017.
 */

public class ProcessoDao extends SQLiteOpenHelper{
    public ProcessoDao(Context context){ super(context,"Processo",null,1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE Processo(numeroProcesso INTEGER PRIMARY KEY,juiz TEXT NOT NULL,data DATETIME NOT NULL,comarca TEXT NOT NULL, tipoProcesso TEXT NOT NULL, orgaoJugador TEXT NOT NULL, classeAcao TEXT NOT NULL, situacao TEXT NOT NULL, assunto TEXT NOT NULL,informacao TEXT NOT NULL,parte TEXT NOT NULL, evemto TEXT NOT NULL,);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS Processo";
        db.execSQL(sql);
        onCreate(db);
    }
    public void insere(Processo processo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesProcesso(processo);
        db.insert("Processo",null,dados);
    }
    @NonNull
    private ContentValues getContentValuesProcesso(Processo processo){
        ContentValues dados= new ContentValues();
        dados.put("numeroProcesso",processo.getNumeroProcesso());
        dados.put("data",processo.getData().toString());
        dados.put("juiz",processo.getJuiz());
        dados.put("comarca",processo.getComarca());
        dados.put("tipoProcesso",processo.getTipoProcesso());
        dados.put("orgaoJugador",processo.getOrgaoJugador());
        dados.put("classeAcao",processo.getClasseAcao());
        dados.put("situacao",processo.getSituacao());
        dados.put("assunto",processo.getAssuntos().toString());
        dados.put("informacao",processo.getInformacoesAdicionaises().toString());
        dados.put("parte",processo.getPartes().toString());
        dados.put("evento",processo.getEventos().toString());
        return dados;
    }

    public List<Processo> busca(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Processo where ;";
        Cursor c = db.rawQuery(sql, null);

        List<Processo> processos= new ArrayList<Processo>();
        while (c.moveToNext()) {
            Processo processo = new Processo();
            processo.setNumeroProcesso(c.getInt(c.getColumnIndex("numeroProcesso")));
          //  processo.setData(c.getString(c.getColumnIndex("data")));
            processo.setJuiz(c.getString(c.getColumnIndex("juiz")));
            processo.setComarca(c.getString(c.getColumnIndex("comarca")));
            processo.setTipoProcesso(c.getString(c.getColumnIndex("tipoProcesso")));
            processo.setOrgaoJugador(c.getString(c.getColumnIndex("orgaoJugador")));
            processo.setClasseAcao(c.getString(c.getColumnIndex("classeAcao")));
            processo.setSituacao(c.getString(c.getColumnIndex("situacao")));
         //   processo.setAssuntos(c.getString(c.getColumnIndex("assunto")));
         //   processo.setInformacoesAdicionaises(c.getString(c.getColumnIndex("informacao")));
         //   processo.setPartes(c.getString(c.getColumnIndex("parte")));
          //  processo.setEventos(c.getString(c.getColumnIndex("evento")));
            processos.add(processo);
        }
        c.close();
        return processos;
    }

    public void deleta(Processo processo) {
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {processo.getNumeroProcesso().toString()};
        db.delete("Processo", "numeroProcesso = ?", params);
    }

    public void altera(Processo processo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesProcesso(processo);
        String[] params = {processo.getNumeroProcesso().toString()};
        db.update("Processo", dados, "numeroProcesso = ?", params);
    }
}
