package com.example.marquesdesouza.appeproc.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.marquesdesouza.appeproc.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marques de Souza on 03/01/2017.
 */

public class UsuarioDao extends SQLiteOpenHelper {
    public UsuarioDao(Context context) {
        super(context, "Usuario", null, 1);
    }
    private static final String LOGIN="login";
    private static final String SENHA="senha";
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuario (login TEXT NOT NULL,senha TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Usuario";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesUsuario(usuario);
        db.insert("Usuario",null,dados);
    }

    @NonNull
    private ContentValues getContentValuesUsuario(Usuario usuario) {
        ContentValues dados = new ContentValues();
        dados.put("login", usuario.getLogin());
        dados.put("senha", usuario.getSenha());
        return dados;
    }

    public List<Usuario> busca(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Usuario;";
        Cursor c = db.rawQuery(sql, null);

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while (c.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setLogin(c.getString(c.getColumnIndex("login")));
            usuario.setSenha(c.getString(c.getColumnIndex("senha")));

            usuarios.add(usuario);
        }
        c.close();
        return usuarios;
    }
    public String getUsuario(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Usuario;";
        Cursor c = db.rawQuery(sql, null);
        String login = new String();
        while (c.moveToNext()) {

            login = c.getString(c.getColumnIndex("login"));

            c.close();
        }
        if(login.isEmpty()){
            return null;
        }
        return login;
    }
    public String getSenha(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Usuario;";
        Cursor c = db.rawQuery(sql, null);

        String senha=new String();
        while (c.moveToNext()) {
            senha = (String) c.getString(c.getColumnIndex("senha"));
        } if(senha.isEmpty()){
            return null;
        }
        c.close();
        return senha;
    }

    public void deleta(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {usuario.getLogin()};
        db.delete("Usuario", "login = ?", params);
    }
    public void limpar() {
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {"remover"};
        db.delete("Usuario", "'remover' = ?", params);
    }
    public void altera(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesUsuario(usuario);
        String[] params = {usuario.getLogin()};
        db.update("Usuario", dados, "login = ?", params);
    }

}
