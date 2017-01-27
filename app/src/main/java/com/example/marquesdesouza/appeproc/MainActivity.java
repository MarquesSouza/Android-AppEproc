package com.example.marquesdesouza.appeproc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.marquesdesouza.appeproc.DAO.UsuarioDao;
import com.example.marquesdesouza.appeproc.model.Usuario;

import java.util.List;
import java.util.logging.LogManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton btn1=(ImageButton) findViewById(R.id.imgBt1);
        final ImageButton btn2=(ImageButton) findViewById(R.id.imgBt2);

        final UsuarioDao dao=new UsuarioDao(this);
        final Usuario usuarios=new Usuario();
        if((dao.getUsuario()==null)||(dao.getSenha()==null)){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarios.setLogin("anonimo");
                usuarios.setSenha("anonimo");
                dao.insere(usuarios);
                Log.d(null, usuarios.getLogin().toString());
                Log.d(null, usuarios.getSenha().toString());
                Intent aninimo= new Intent(MainActivity.this,MeusProcessoActivity.class);
              startActivity(aninimo);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eproc=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(eproc);
            }
        });
    }else{
          Intent go= new Intent(MainActivity.this,MeusProcessoActivity.class);
          Bundle dados= new Bundle();
           dados.putString("login",dao.getUsuario());
           dados.putString("senha",dao.getSenha());
           go.putExtras(dados);
          startActivity(go);
       }
        dao.close();
    }
}
