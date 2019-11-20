package br.com.lojadigicom.coletorprecocliente.app;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListAdapter;
import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListViewHolder;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeDiaContract;
import br.com.lojadigicom.coletorprecocliente.data.provider.OportunidadeDiaConsulta;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.detalhe.NaturezaProdutoDetalheBaseActivity;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListFragmentBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListViewHolderBase;

import static com.facebook.FacebookSdk.getApplicationContext;


public class OportunidadeDiaListFragment extends OportunidadeDiaListFragmentBase {



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




    @Override
    protected Uri getUri() {
        return OportunidadeDiaContract.buildGetPorPertenceANaturezaProdutoUri(mIdNaturezaProduto);
    }
    @Override
    protected String getColunaOrdenacao() {
        return OportunidadeDiaContract.COLUNA_DATA_ULTIMA_PRECO_ANTERIOR + " desc ";
    }
    @Override
    protected MontadorDaoI getMontador() {
        return OportunidadeDiaConsulta.getListaPorNaturezaProdutoMontador();
    }



    protected Class getOnClickLista() {
        return OportunidadeDiaDetalheActivity.class;
    }
    protected void montaAminacao(OportunidadeDiaListViewHolderBase vh, ArrayList<Pair<View,String>> lista) {
        //OportunidadeDiaListViewHolder holder = (OportunidadeDiaListViewHolder) vh;
        //lista.add(Pair.create((View)holder.mNomeProduto, "trNomeProduto"));
        //lista.add(Pair.create((View)holder.mPrecoAtual, "trPrecoAtual"));
    }

    @Override
    protected String getTextoListaVazia() {
        return "Lista vazia";
    }


}
