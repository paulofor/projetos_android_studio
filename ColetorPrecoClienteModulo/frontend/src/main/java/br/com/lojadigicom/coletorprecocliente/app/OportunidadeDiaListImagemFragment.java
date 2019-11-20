package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;

import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListAdapter;
import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListImagemAdapter;
import br.com.lojadigicom.coletorprecocliente.adapter.OportunidadeDiaListImagemViewHolder;
import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeDiaContract;
import br.com.lojadigicom.coletorprecocliente.data.provider.OportunidadeDiaConsulta;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListFragmentBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListViewHolderBase;


public class OportunidadeDiaListImagemFragment extends OportunidadeDiaListFragmentBase {



    @Override
    protected String getTextoListaVazia() {
        return "Lista vazia";
    }

    @Override
    protected void inicializaListaInferior(SharedPreferences.Editor edt) {

    }

    @Override
    protected OportunidadeDiaListAdapterBase getListAdapter(FragmentActivity activity, OportunidadeDiaListAdapterBase.OportunidadeDiaListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new OportunidadeDiaListImagemAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }

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
        OportunidadeDiaListImagemViewHolder holder = (OportunidadeDiaListImagemViewHolder) vh;
        lista.add(Pair.create((View)holder.mNomeProduto, "trNomeProduto"));
        lista.add(Pair.create((View)holder.mPrecoAtual, "trPrecoAtual"));
        lista.add(Pair.create((View)holder.mImgemProduto, "trImagemProduto"));
    }
}
