package br.com.lojadigicom.capitalexterno.tela;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;

import br.com.lojadigicom.capitalexterno.data.contract.NegocioContract;
import br.com.lojadigicom.capitalexterno.lista.NegocioListAdapter;
import br.com.lojadigicom.capitalexterno.template.lista.NegocioListAdapterBase;
import br.com.lojadigicom.capitalexterno.template.lista.NegocioListFragmentBase;
import br.com.lojadigicom.capitalexterno.template.lista.NegocioListViewHolderBase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NegocioListaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NegocioListaFragment extends NegocioListFragmentBase {

    @Override
    protected String getTextoListaVazia() {
       return "Nenhum negocio criado";
    }


    @Override
    protected Class getOnClickLista() {
        return NegocioDetalheActivity.class;
    }

    @Override
    protected void montaAminacao(NegocioListViewHolderBase vh, ArrayList<Pair<View, String>> lista) {

    }

    @Override
    protected void inicializaListaInferior(SharedPreferences.Editor edt) {

    }

    @Override
    protected NegocioListAdapterBase getListAdapter(FragmentActivity activity, NegocioListAdapterBase.NegocioListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new NegocioListAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }



    @Override
    protected Uri getUri() {
        return  NegocioContract.buildAll();
    }

    @Override
    protected String getColunaOrdenacao() {
        return NegocioContract.COLUNA_DESCRICAO;
    }
}
