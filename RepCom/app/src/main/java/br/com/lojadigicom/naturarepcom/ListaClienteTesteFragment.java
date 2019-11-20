package br.com.lojadigicom.naturarepcom;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.naturarepcom.adapter.ClienteListAdapter;
import br.com.lojadigicom.repcom.MyAdapter;
import br.com.lojadigicom.repcom.data.contract.ClienteContract;
import br.com.lojadigicom.repcom.framework.data.DCCursorLoader;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.Cliente;
import br.com.lojadigicom.repcom.modelo.ClienteVo;
import br.com.lojadigicom.repcom.template.lista.ClienteListAdapterBase;
import br.com.lojadigicom.repcom.template.lista.ClienteListViewHolderBase;


public class ListaClienteTesteFragment extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mPosition = RecyclerView.NO_POSITION;
    private int mChoiceMode;

    public interface Callback {
        //public void onItemSelected(Uri dateUri, NaturezaProdutoListAdapter.NaturezaProdutoListAdapterViewHolder vh);
        public void onItemSelected(int idItemLista, ClienteListViewHolderBase vh);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DCLog.d(DCLog.DISPLAY,this,"onCreateView2");
        //recuperaPosicao();
        View rootView = inflater.inflate(R.layout.lista_padrao, container, false);
        super.onCreate(savedInstanceState);
        View emptyView = rootView.findViewById(br.com.lojadigicom.repcom.template.R.id.txt_lista_padrao_vazia);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rec_lista_padrao);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = getListAdapter(getActivity(), new ClienteListAdapterBase.ClienteListAdapterOnClickHandler() {
            @Override
            public void onClick(int idItemLista, ClienteListViewHolderBase vh) {
                //String locationSetting = Utility.getPreferredLocation(getActivity());
                ((Callback) getActivity()).onItemSelected(idItemLista, vh);
                //registraPosicao(vh);
                mPosition = vh.getAdapterPosition();
            }
        }, emptyView, mChoiceMode);

        // specify an adapter (see also next example)
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    protected ClienteListAdapterBase getListAdapter(FragmentActivity activity, ClienteListAdapterBase.ClienteListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode) {
        return new ClienteListAdapter(activity,onClickHandler,emptyView,mChoiceMode);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = ClienteContract.buildAll();
        return new DCCursorLoader(getActivity(),uri,null,null,null,ClienteContract.COLUNA_NOME + " asc ");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<Cliente> listaCliente = ClienteContract.converteLista(data);
        ((ClienteListAdapter)mAdapter).swapCursor(listaCliente);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
