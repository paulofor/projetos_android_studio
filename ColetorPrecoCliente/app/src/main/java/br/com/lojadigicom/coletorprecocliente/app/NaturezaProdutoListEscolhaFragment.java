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

import br.com.lojadigicom.coletorprecocliente.adapter.NaturezaProdutoListEdicaoAdapter;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioPesquisaContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoException;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.UsuarioPesquisaMontador;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListFragmentBase;

/**
 * Created by Paulo on 18/02/16.
 */
public class NaturezaProdutoListEscolhaFragment extends NaturezaProdutoListFragmentBase {
    @Override
    protected void inicializaListaInferior(SharedPreferences.Editor edt) {

    }

    @Override
    protected NaturezaProdutoListAdapterBase getListAdapter(FragmentActivity activity, NaturezaProdutoListAdapterBase.NaturezaProdutoListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new NaturezaProdutoListEdicaoAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }



    /*@Override
    protected List<NaturezaProduto> converteLista(Cursor data) {
        MontadorDaoComposite montador = NaturezaProdutoContract.getMontadorComposto();
        montador.adicionaMontador(new UsuarioPesquisaMontador(),"UsuarioPesquisa_PesquisadoPor");
        return NaturezaProdutoContract.converteLista(data,montador);
    }*/

    @Override
    protected MontadorDaoI getMontador() {
        MontadorDaoComposite montador = NaturezaProdutoContract.getMontadorComposto();
        montador.adicionaMontador(new UsuarioPesquisaMontador(),"UsuarioPesquisa_PesquisadoPor");
        return montador;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = NaturezaProdutoContract.buildAllComUsuarioPesquisaPesquisadoPor();
        //String[] campos = NaturezaProdutoContract
        return new DCCursorLoader(getActivity(),uri,null,null,null,NaturezaProdutoContract.COLUNA_NOME_NATUREZA_PRODUTO);
    }
}
