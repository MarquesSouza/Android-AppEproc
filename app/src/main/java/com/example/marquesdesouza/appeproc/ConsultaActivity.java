package com.example.marquesdesouza.appeproc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.marquesdesouza.appeproc.DAO.UsuarioDao;
import com.example.marquesdesouza.appeproc.model.Assunto;
import com.example.marquesdesouza.appeproc.model.Evento;
import com.example.marquesdesouza.appeproc.model.InformacoesAdicionais;
import com.example.marquesdesouza.appeproc.model.Parte;
import com.example.marquesdesouza.appeproc.model.Processo;
import com.example.marquesdesouza.appeproc.model.WebService;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private Processo processo=new Processo();
    private EditText edt;
    private WebService web=new WebService();
    private Button btnPesq;
    private RadioButton nome,cnpj,cpf,numeroProcesso;
    public List<Processo> processos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final ListView listProcesso;
        nome=(RadioButton)findViewById(R.id.rbtnome);
        cpf=(RadioButton)findViewById(R.id.rbtcpf);
        cnpj=(RadioButton)findViewById(R.id.rbtcnpj);
        numeroProcesso=(RadioButton)findViewById(R.id.rbtnumero);
        edt=(EditText)findViewById(R.id.edtPesquisa);
        listProcesso=(ListView)findViewById(R.id.listaconsulta);
        registerForContextMenu(listProcesso);
        btnPesq=(Button)findViewById(R.id.btnpesquisa);
        btnPesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(nome.isChecked()){
                        dlg.setMessage("Erro ta aqui!"+edt.getText().toString());
                        dlg.setNeutralButton("OK", null);
                        dlg.show();
                        List<Processo> processos=(List<Processo>)web.ConsultaNome(edt.getText().toString());
                        ArrayAdapter<Processo> adapter= new ArrayAdapter<Processo>(ConsultaActivity.this,android.R.layout.simple_spinner_item,processos);
                        listProcesso.setAdapter(adapter);

                    }else{
                if(cpf.isChecked()){
                    List<Processo> processos=web.ConsultaCPF(edt.getText().toString());
                    ArrayAdapter<Processo> adapter= new ArrayAdapter<Processo>(ConsultaActivity.this,android.R.layout.simple_list_item_2,processos);
                    listProcesso.setAdapter(adapter);
                }else {
                if(cnpj.isChecked()){
                    List<Processo> processos=web.ConsultaCNPJ(edt.getText().toString());
                   ArrayAdapter<Processo> adapter= new ArrayAdapter<Processo>(ConsultaActivity.this,android.R.layout.simple_list_item_2,processos);
                    listProcesso.setAdapter(adapter);
                }else{if(numeroProcesso.isChecked()){
                   List<Processo> processos=web.ConsultaNumero(edt.getText().toString());
                   ArrayAdapter<Processo> adapter= new ArrayAdapter<Processo>(ConsultaActivity.this,android.R.layout.simple_list_item_2,processos);
                    listProcesso.setAdapter(adapter);
                }}}}
            }
        });





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.consulta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final AlertDialog.Builder dlg = new AlertDialog.Builder(ConsultaActivity.this);
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_pesquisar) {
            dlg.setMessage("Essa e A pagina de pesquisa");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }else{
            if(id== R.id.menu_adicionar){
                dlg.setMessage("Adicionar: (Em desenvolvimento!)");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }else {
                if(id==R.id.menu_Excluir){
                    dlg.setMessage("Excluir: (Em desenvolvimento!)");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        final AlertDialog.Builder dlg = new AlertDialog.Builder(ConsultaActivity.this);
        if(id == R.id.menu_alterar){
            UsuarioDao dao=new UsuarioDao(this);
            dao.limpar();
            Intent go= new Intent(ConsultaActivity.this,MainActivity.class);
            startActivity(go);
        }else{
            if(id== R.id.menu_tutorial){
                dlg.setMessage("Tutorial: (Em desenvolvimento!)");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }else{
                if(id== R.id.menu_ajuda){
                    dlg.setMessage("Ajuda: (Em desenvolvimento!)");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }else{
                    if(id==R.id.menu_autor){
                        dlg.setMessage("Autor:\n IFTO-Campus Paraiso do Tocantins \n Sistemas de Informação 4 periodo \n Marques de Souza Almeida \n AppEproc ");
                        dlg.setNeutralButton("OK", null);
                        dlg.show();
                    }
                }
            }

        }
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,final ContextMenu.ContextMenuInfo menuInfo){
        MenuItem menuContexto=menu.add("detalhes");
        menuContexto.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem menuItem){
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                return false;
            }
        });
         menuContexto=menu.add("adicionar");
        menuContexto.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem menuItem){
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                return false;
            }
        });}
    }

