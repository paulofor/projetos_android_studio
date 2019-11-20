package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.adapter.InteresseProdutoEsperaAdapter;
import br.com.lojadigicom.coletorprecocliente.adapter.InteresseProdutoEsperaViewHolder;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListFragmentBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListViewHolderBase;

public class ListaEsperaFragment extends InteresseProdutoListFragmentBase {



    @Override
    protected Class getOnClickLista() {
        return ListaEsperaDetalheActivity.class;
    }

    @Override
    protected void montaAminacao(InteresseProdutoListViewHolderBase vh, ArrayList<Pair<View, String>> lista) {
        InteresseProdutoEsperaViewHolder holder = (InteresseProdutoEsperaViewHolder)vh;
        lista.add(Pair.create((View)holder.txtNomeProduto,"trTxtNomeProduto"));
        lista.add(Pair.create((View)holder.imgProduto,"trImgProduto"));
    }

    @Override
    protected String getTextoListaVazia() {
        return "Nenhum produto marcado para espera";
    }

    @Override
    protected void inicializaListaInferior(SharedPreferences.Editor edt) {

    }

    @Override
    protected InteresseProdutoListAdapterBase getListAdapter(FragmentActivity activity, InteresseProdutoListAdapterBase.InteresseProdutoListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new InteresseProdutoEsperaAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }

    @Override
    protected Uri getUri() {
        return InteresseProdutoContract.buildListaEspera();
    }

    @Override
    protected String getColunaOrdenacao() {
        return InteresseProdutoContract.TABLE_NAME + "." + InteresseProdutoContract.COLUNA_DATA  + " desc ";
    }


    @Override
    protected MontadorDaoI getMontador() {
        MontadorDaoI composite = InteresseProdutoContract.getMontadorComposto();
        return InteresseProdutoContract.adicionaMontadorProdutoClienteReferenteA(composite);
    }
}
