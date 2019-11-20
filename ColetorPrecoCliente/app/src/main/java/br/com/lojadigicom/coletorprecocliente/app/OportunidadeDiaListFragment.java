package br.com.lojadigicom.coletorprecocliente.app;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListAdapter;
import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeDiaContract;
import br.com.lojadigicom.coletorprecocliente.data.provider.OportunidadeDiaConsulta;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DBHelperBase;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.modelo.OportunidadeDia;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListFragmentBase;


public class OportunidadeDiaListFragment extends OportunidadeDiaListFragmentBase {

    private long mIdNaturezaProduto;

    @Override
    protected void inicializaListaInferior(SharedPreferences.Editor edt) {

    }

    @Override
    protected OportunidadeDiaListAdapterBase getListAdapter(FragmentActivity activity, OportunidadeDiaListAdapterBase.OportunidadeDiaListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new OportunidadeDiaListAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }
/*
    @Override
    protected List<OportunidadeDia> converteLista(Cursor data) {
        List lista = new ArrayList();
        try {
            lista = DBHelperBase.getListaSqlListaInterna(data, OportunidadeDiaConsulta.getListaPorNaturezaProdutoMontador(), this);
        } catch (Exception e) {
            lista = new ArrayList();
        }
        return lista;
    }*/

    public void setIdNaturezaProduto(long id) {
        mIdNaturezaProduto = id;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = OportunidadeDiaContract.buildGetPorPertenceANaturezaProdutoUri(mIdNaturezaProduto);
        return new DCCursorLoader(getActivity(),uri,null,null,null,OportunidadeDiaContract.COLUNA_DATA_ULTIMA_PRECO_ANTERIOR + " desc ", OportunidadeDiaConsulta.getListaPorNaturezaProdutoMontador());
    }


}
