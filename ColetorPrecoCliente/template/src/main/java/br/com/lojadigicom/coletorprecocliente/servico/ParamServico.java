package br.com.lojadigicom.coletorprecocliente.servico;

import android.content.ContentResolver;
import android.content.Context;

import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.servico.filtro.IFiltro;

/**
 * Created by Paulo on 24/02/16.
 */
public class ParamServico {

    private String nomeOperacao = null;
    private Context ctx = null;
    private IFiltro filtro = null;
    private int idOperacao = 0;
    private IServico srv = null;
    private DCIObjetoDominio item = null;

    public ParamServico(Context val1, int val2, IFiltro val3, IServico val4) {
        ctx = val1;
        idOperacao = val2;
        filtro = val3;
        srv = val4;
    }

    public ParamServico(Context val1, IServico val2) {
        ctx = val1;
        srv = val2;
    }

    public void setFiltro(IFiltro val) {
        filtro = val;
    }
    public void setIdOperacao(int val) {
        idOperacao = val;
    }

    public IFiltro getFiltro() {
        return filtro;
    }
    public Context getContext() {
        return ctx;
    }
    public int getIdOperacao() {
        return idOperacao;
    }
    public IServico getServico() {
        return srv;
    }

    public void setItem(DCIObjetoDominio valor) {
        item = valor;
    }
    public DCIObjetoDominio getItem() {
        return item;
    }
}
