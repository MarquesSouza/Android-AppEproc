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
import android.widget.Button;
import android.widget.ListView;

import com.example.marquesdesouza.appeproc.DAO.UsuarioDao;

public class MeusProcessoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_processo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ListView listaMeus=(ListView)findViewById(R.id.lista_processo);
        registerForContextMenu(listaMeus);
        Button novaConsulta =(Button) findViewById(R.id.btnNovaConsulta);
        novaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consulta=new Intent(MeusProcessoActivity.this,ConsultaActivity.class);
                startActivity(consulta);
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
        getMenuInflater().inflate(R.menu.meus_processo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final AlertDialog.Builder dlg = new AlertDialog.Builder(MeusProcessoActivity.this);
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_pesquisar) {
            Intent consulta=new Intent(MeusProcessoActivity.this,ConsultaActivity.class);
            startActivity(consulta);

        }else{
            if(id== R.id.menu_adicionar){
                Intent consulta=new Intent(MeusProcessoActivity.this,ConsultaActivity.class);
                startActivity(consulta);
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
        final AlertDialog.Builder dlg = new AlertDialog.Builder(MeusProcessoActivity.this);
        if(id == R.id.menu_alterar){
            UsuarioDao dao=new UsuarioDao(this);
            dao.limpar();
            Intent go= new Intent(MeusProcessoActivity.this,MainActivity.class);
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
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo){
        MenuItem menuContexto=menu.add("detalhes");
        menuContexto.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem menuItem){
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                return false;
            }
        });
        menuContexto=menu.add("excluir");
        menuContexto.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem menuItem){
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                return false;
            }
        });
    }
}
