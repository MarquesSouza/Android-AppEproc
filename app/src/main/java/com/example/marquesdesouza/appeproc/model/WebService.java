package com.example.marquesdesouza.appeproc.model;
import com.example.marquesdesouza.appeproc.ConsultaActivity;
import com.example.marquesdesouza.appeproc.LoginActivity;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

/**
 * Created by Marques de Souza on 06/01/2017.
 */

public class WebService {
    public List<Processo> processos;
    public void WebService(){

        Processo processo=new Processo();
        processo.setNumeroProcesso(203);
        processo.setComarca("paraiso");
        processo.setJuiz("Jose Marcos");
        processo.setTipoProcesso("Indenizacao");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateInString="7-Jun-2013";
        Date date=new Date();
        try{
            date=formatter.parse(dateInString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        processo.setData(date);
        List<Parte> partes=new ArrayList<Parte>();
        Parte parte1= new Parte();
        Parte parte2= new Parte();
        Parte parte3= new Parte();
        parte1.setDocumento("000.000.000-00");
        parte1.setNome("fulano");
        parte1.setTipoDocumento("cpf");
        parte1.setTipo("reu");
        parte1.setDocumento("000.000.000-00");
        parte1.setNome("fulano1");
        parte1.setTipoDocumento("cpf");
        parte1.setTipo("reu");
        parte1.setDocumento("000.000.000-00");
        parte1.setNome("fulano2");
        parte1.setTipoDocumento("cpf");
        parte1.setTipo("reu");
        partes.add(parte1);
        partes.add(parte2);
        partes.add(parte3);
        processo.setPartes(partes);
        Evento evento1 = new Evento();
        Evento evento2 = new Evento();
        Evento evento3 = new Evento();
        evento1.setDescricao("descricao1");
        evento1.setDocumento("documento1");
        evento1.setUsuario("usuario1");
        evento1.setNumero(1);
        evento1.setData(date);
        evento2.setUsuario("etc etc etc etc");
        evento2.setDescricao("descricao2");
        evento2.setDocumento("documento2");
        evento2.setNumero(2);
        evento2.setData(date);
        evento3.setUsuario("etc etc etc etc222");
        evento3.setDescricao("descricao3");
        evento3.setDocumento("blabla3");
        evento3.setNumero(3);
        evento3.setData(date);
        List<Evento>eventos=new ArrayList<Evento>();
        eventos.add(evento1);
        eventos.add(evento2);
        eventos.add(evento3);
        processo.setEventos(eventos);
        List<Assunto> assuntos=new ArrayList<Assunto>();
        Assunto assunto1=new Assunto();
        Assunto assunto2=new Assunto();
        assunto1.setCodigo(1);
        assunto1.setDescricao("descricao assunto1");
        assunto1.setPrincipal("nao");
        assunto2.setCodigo(2);
        assunto2.setDescricao("descriacao assunto 2");
        assunto2.setPrincipal("sim");
        assuntos.add(assunto1);
        assuntos.add(assunto2);
        processo.setAssuntos(assuntos);
        List<InformacoesAdicionais>informacoesAdicionaises=new ArrayList<InformacoesAdicionais>();
        InformacoesAdicionais info=new InformacoesAdicionais();
        info.setValor(1100.999);
        info.setPeticaoUrgente("nao");
        info.setVistaMinisterio("nao");
        info.setAntecipacaoTutelar("nao");
        info.setPrioridade("nao");
        info.setJusticaGratuita("sim");
        info.setReuPreso("sim");
        informacoesAdicionaises.add(info);
        processo.setInformacoesAdicionaises(informacoesAdicionaises);
        processos.add(processo);

    }
    public boolean verificarUsuario(String usuario,String senha){

        if((usuario.equals("marques"))&&(senha.equals("123"))){
            return true;
        }
        if((usuario.equals("admin"))&&(senha.equals("admin"))){
            return true;
        }
        if((usuario.equals("admin"))&&(senha.equals("123"))){
            return true;
        }

        return false;
    }



    public List<Processo> ConsultaCPF(String cpf){
        List<Parte> partesTemp = new ArrayList<Parte>();
        List<Processo> processosResult = new ArrayList<Processo>();
        for (Processo processo:processos){
            partesTemp= processo.getPartes();
            for(Parte parte: partesTemp){
                if((cpf.equals(parte.getDocumento()))&&("cpf".equals(parte.getTipoDocumento()))){
                    processosResult.add(processo);
                    break;
                }
            }
        }
        return processosResult;
    }
    public List<Processo> ConsultaCNPJ(String cnpj){

        List<Parte> partesTemp = new ArrayList<Parte>();
        List<Processo> processosResult = new ArrayList<Processo>();
        for (Processo processo:processos){
            partesTemp= processo.getPartes();
            for(Parte parte: partesTemp){
                if((cnpj.equals(parte.getDocumento()))&&("cnpj".equals(parte.getTipoDocumento()))){
                    processosResult.add(processo);
                    break;
                }
            }
        }
        return processosResult;
    }
    public List<Processo> ConsultaNome(String nome){
        List<Parte> partesTemp = new ArrayList<Parte>();
        List<Processo> processosResult = new ArrayList<Processo>();
        for (Processo processo:processos){
            partesTemp= processo.getPartes();
            for(Parte parte: partesTemp){
                if(nome.equals(parte.getNome())){
                    processosResult.add(processo);
                    break;
                }
            }
        }
        return processosResult;
    }
    public List<Processo> ConsultaNumero(String numero){
        List<Parte> partesTemp = new ArrayList<Parte>();
        List<Processo> processosResult = new ArrayList<Processo>();
        for (Processo processo:processos){
            if(numero.equals(processo.getNumeroProcesso())){
                    processosResult.add(processo);
                    break;
                }
            }

        return processosResult;
    }
    public Processo retornaProcesso(Integer numeroProcesso){
        Processo processoRetorno=new Processo();
        for (Processo processo:processos){
            if(numeroProcesso==processo.getNumeroProcesso()){
                processoRetorno = processo;
                break;
            }
        }
        return processoRetorno;
    }

    public String retornaProcessoJson(Integer numeroProcesso){
        String processoRetorno=null;
        Gson gson= new Gson();
        for (Processo processo:processos){
            if(numeroProcesso==processo.getNumeroProcesso()){
                processoRetorno = gson.toJson(processo);
                break;
            }
        }// addcionar como e um numero de processo ,longtext descricao
        return processoRetorno;
    }
    public Processo retornoProcessoObj(String json){
        Gson gson= new Gson();
        Processo processo=gson.fromJson(json,Processo.class);
        return processo;
    }

}
