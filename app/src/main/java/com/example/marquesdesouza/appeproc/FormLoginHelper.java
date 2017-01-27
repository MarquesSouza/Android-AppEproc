package com.example.marquesdesouza.appeproc;

import android.widget.EditText;

import com.example.marquesdesouza.appeproc.model.Usuario;

/**
 * Created by Marques de Souza on 05/01/2017.
 */

public class FormLoginHelper {
    private EditText login;
    private EditText senha;
    private Usuario usuario;

    public FormLoginHelper(LoginActivity activity){
        login=(EditText) activity.findViewById(R.id.edtlogin);
        senha=(EditText) activity.findViewById(R.id.edtsenha);
        usuario=new Usuario();
    }
    public Usuario pegaUsuario(){
        usuario.setLogin(login.getText().toString());
        usuario.setSenha(senha.getText().toString());
        return usuario;
    }
    public void preencherForm(Usuario usuario){
        login.setText(usuario.getLogin());
        senha.setText(usuario.getSenha());
        this.usuario=usuario;
    }


}
