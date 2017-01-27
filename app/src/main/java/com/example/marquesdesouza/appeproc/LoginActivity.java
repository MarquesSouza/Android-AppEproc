package com.example.marquesdesouza.appeproc;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.marquesdesouza.appeproc.DAO.UsuarioDao;
import com.example.marquesdesouza.appeproc.model.Usuario;
import com.example.marquesdesouza.appeproc.model.WebService;

public class LoginActivity extends AppCompatActivity {
    private FormLoginHelper helper;
    private WebService webService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       helper=new FormLoginHelper(this);
        final UsuarioDao dao=new UsuarioDao(this);
        Button btn=(Button) findViewById(R.id.btnlogin);
            webService=new WebService();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario=helper.pegaUsuario();
              if(webService.verificarUsuario(usuario.getLogin().toString(),usuario.getSenha().toString())==true){
                dao.insere(usuario);
                Intent eproc=new Intent(LoginActivity.this,MeusProcessoActivity.class);
                startActivity(eproc);
              }else{
                AlertDialog.Builder dlg = new AlertDialog.Builder(LoginActivity.this);
                   dlg.setMessage("Usuario invalido!");
                   dlg.setNeutralButton("OK", null);
                   dlg.show();
             }
            }
        });

    }
}
