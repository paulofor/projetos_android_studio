package br.com.lojadigicom.naturarepcom;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.naturarepcom.adapter.ClienteListAdapter;
import br.com.lojadigicom.repcom.data.contract.ClienteContract;
import br.com.lojadigicom.repcom.framework.dao.DBHelperBase;
import br.com.lojadigicom.repcom.framework.data.DCCursorLoader;
import br.com.lojadigicom.repcom.modelo.Cliente;
import br.com.lojadigicom.repcom.template.lista.ClienteListAdapterBase;
import br.com.lojadigicom.repcom.template.lista.ClienteListFragmentBase;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListaClienteActivityFragment extends ClienteListFragmentBase {

    public ListaClienteActivityFragment() {
    }

    @Override
    protected void inicializaListaInferior(SharedPreferences.Editor edt) {

    }

    @Override
    protected ClienteListAdapterBase getListAdapter(FragmentActivity activity, ClienteListAdapterBase.ClienteListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new ClienteListAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = ClienteContract.buildAll();
        return new DCCursorLoader(getActivity(),uri,null,null,null,ClienteContract.COLUNA_NOME + " asc ");
    }
}
