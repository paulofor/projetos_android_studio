package br.com.lojadigicom.coletorprecocliente.app;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


import br.com.lojadigicom.coletorprecocliente.adapter.NaturezaProdutoListAdapter;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoException;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListFragmentBase;


/**
 * Created by Paulo on 09/11/15.
 */
public class NaturezaProdutoListFragment extends NaturezaProdutoListFragmentBase {


    private int mPosition = RecyclerView.NO_POSITION;

    @Override
    protected void inicializaListaInferior(SharedPreferences.Editor edt) {
        edt.putInt(Constantes.OPORTUNIDADE_DIA_POSICAO,RecyclerView.NO_POSITION);
    }

    @Override
    protected NaturezaProdutoListAdapterBase getListAdapter(FragmentActivity activity, NaturezaProdutoListAdapterBase.NaturezaProdutoListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new NaturezaProdutoListAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }

    /*@Override
    protected List<NaturezaProduto> converteLista(Cursor data) {
        List lista = new ArrayList();
        try {
            lista = getListaSqlListaInterna(data, NaturezaProdutoContract.getMontador());
        } catch (Exception e) {
            lista = new ArrayList();
        }
        return lista;
    }

    protected List getListaSqlListaInterna(Cursor c, MontadorDaoI montador) throws DaoException {
        ArrayList<DCIObjetoDominio> listaSaida = new ArrayList<DCIObjetoDominio>();
        int numRows = c.getCount();
        boolean insere = false;
        DCIObjetoDominio objeto = null;
        DaoItemRetorno retorno = null;
        while (c.moveToNext()) {
            try {
                retorno = montador.extraiRegistroListaInterna(c, objeto);
                insere = retorno.getInsere();
                objeto = retorno.getObjeto();
            } catch (Exception e) {
                DCLog.e(DCLog.DATABASE_ERRO_CORE, this, e);
                //this.erroException(e, this);
                //throw new DaoException(e,sql);
            }
            if (insere) {
                listaSaida.add(objeto);
            }
        }
        return listaSaida;
    }
    */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //Uri uri = NaturezaProdutoContract.buildAll();
        //String[] campos = NaturezaProdutoContract
        Uri uri = NaturezaProdutoContract.buildListaAtivo();
        return new DCCursorLoader(getActivity(),uri,null,null,null,null);
    }



}
