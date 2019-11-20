package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.adapter.InteresseProdutoEsperaAdapter;
import br.com.lojadigicom.coletorprecocliente.adapter.InteresseProdutoEsperaViewHolder;
import br.com.lojadigicom.coletorprecocliente.adapter.InteresseProdutoMonitoraAdapter;
import br.com.lojadigicom.coletorprecocliente.adapter.InteresseProdutoMonitoraViewHolder;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListFragmentBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListViewHolderBase;


public class ListaMonitoramentoFragment extends InteresseProdutoListFragmentBase {

    @Override
    protected Class getOnClickLista() {
        return ListaMonitoramentoDetalheActivity.class;
    }

    @Override
    protected void montaAminacao(InteresseProdutoListViewHolderBase vh, ArrayList<Pair<View, String>> lista) {
        InteresseProdutoMonitoraViewHolder holder = (InteresseProdutoMonitoraViewHolder)vh;
        lista.add(Pair.create((View)holder.txtNomeProduto,"trTxtNomeProduto"));
        lista.add(Pair.create((View)holder.imgProduto,"trImgProduto"));
        if (holder.rtbRecomendacao.getVisibility()==View.VISIBLE) {
            lista.add(Pair.create((View) holder.rtbRecomendacao, "trRtbRecomendacao"));
        }
        if (holder.txtNovo.getVisibility()==View.VISIBLE) {
            lista.add(Pair.create((View) holder.txtNovo, "trTxtNovo"));
        }
        lista.add(Pair.create((View)holder.txtLoja,"trTxtNomeLoja"));
        lista.add(Pair.create((View)holder.txtPreco,"trTxtPrecoProduto"));
    }

    @Override
    protected String getTextoListaVazia() {
        return "Nenhum produto sendo seguido";
    }

    @Override
    protected void inicializaListaInferior(SharedPreferences.Editor edt) {

    }

    @Override
    protected InteresseProdutoListAdapterBase getListAdapter(FragmentActivity activity, InteresseProdutoListAdapterBase.InteresseProdutoListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new InteresseProdutoMonitoraAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }

    @Override
    protected Uri getUri() {
        return InteresseProdutoContract.buildListaMonitora();
    }

    @Override
    protected String getColunaOrdenacao() {
        return InteresseProdutoContract.TABLE_NAME + "." + InteresseProdutoContract.COLUNA_NOTA + " desc ";
    }

    @Override
    protected MontadorDaoI getMontador() {
        MontadorDaoI composite = InteresseProdutoContract.getMontadorComposto();
        return InteresseProdutoContract.adicionaMontadorProdutoClienteReferenteA(composite);
    }
}
