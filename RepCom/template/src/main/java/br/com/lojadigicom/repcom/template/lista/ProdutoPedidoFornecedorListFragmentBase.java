
package  br.com.lojadigicom.repcom.template.lista;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.lojadigicom.repcom.tela.base.activity.Constantes;
import br.com.lojadigicom.repcom.modelo.ProdutoPedidoFornecedor;
import br.com.lojadigicom.repcom.data.contract.ProdutoPedidoFornecedorContract;
import br.com.lojadigicom.repcom.template.R;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.framework.tela.ResourceObj;
import br.com.lojadigicom.repcom.framework.dao.DBHelperBase;
import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;

public abstract class ProdutoPedidoFornecedorListFragmentBase extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView mRecyclerView;
    private ProdutoPedidoFornecedorListAdapterBase mAdapter;

    private int mPosition = RecyclerView.NO_POSITION;
    private int mChoiceMode;
    private static final String SELECTED_KEY = "selected_position";
    private static final int FORECAST_LOADER = 0;
    private boolean mHoldForTransition;

	protected ResourceObj resourceObj = getLayoutListaResource();
 	//protected int getIdLayoutLista() { return R.layout.lista_padrao; }
    protected int getIdRecLista() { return R.id.rec_lista_padrao; }
    protected int getIdRecListaEmpty() { return R.id.txt_lista_padrao_vazia; }

    public interface Callback {
        //public void onItemSelected(Uri dateUri, NaturezaProdutoListAdapter.NaturezaProdutoListAdapterViewHolder vh);
        public void onItemSelected(int idItemLista, ProdutoPedidoFornecedorListViewHolderBase vh);
    }

    public ProdutoPedidoFornecedorListFragmentBase() {
    }

	protected ResourceObj getLayoutListaResource() {
        return new ResourceObj(R.layout.lista_padrao,"R.layout.lista_padrao");
    }
   


    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DCLog.d(DCLog.DISPLAY,this,this.getClass().getSimpleName());
		//recuperaPosicao();
        View rootView = inflater.inflate(resourceObj.getCodigo(), container, false);

        // Get a reference to the RecyclerView, and attach this adapter to it.
        mRecyclerView = (RecyclerView) rootView.findViewById(getIdRecLista());

        // Set the layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        View emptyView = rootView.findViewById(getIdRecListaEmpty());

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // The ForecastAdapter will take data from a source and
        // use it to populate the RecyclerView it's attached to.
        mAdapter = getListAdapter(getActivity(), new ProdutoPedidoFornecedorListAdapterBase.ProdutoPedidoFornecedorListAdapterOnClickHandler() {
            @Override
            public void onClick(int idItemLista, ProdutoPedidoFornecedorListViewHolderBase vh) {
                //String locationSetting = Utility.getPreferredLocation(getActivity());
                ((Callback) getActivity()).onItemSelected(idItemLista, vh);
                //registraPosicao(vh);
                mPosition = vh.getAdapterPosition();
            }
        }, emptyView, mChoiceMode);

        // specify an adapter (see also next example)
        mRecyclerView.setAdapter(mAdapter);

        // If there's instance state, mine it for useful information.
        // The end-goal here is that the user never knows that turning their device sideways
        // does crazy lifecycle related things.  It should feel like some stuff stretched out,
        // or magically appeared to take advantage of room, but data or place in the app was never
        // actually *lost*.
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SELECTED_KEY)) {
                // The Recycler View probably hasn't even been populated yet.  Actually perform the
                // swapout in onLoadFinished.
                mPosition = savedInstanceState.getInt(SELECTED_KEY);
            }
            mAdapter.onRestoreInstanceState(savedInstanceState);
        }

		SharedPreferences pref = getActivity().getSharedPreferences("br.com.lojadigicom.repcom", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        inicializaListaInferior(edt);
        edt.commit();

        return rootView;
    }

	protected abstract void inicializaListaInferior(SharedPreferences.Editor edt);
    protected abstract ProdutoPedidoFornecedorListAdapterBase getListAdapter(FragmentActivity activity, ProdutoPedidoFornecedorListAdapterBase.ProdutoPedidoFornecedorListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode);

	/*
 	public void registraPosicao(ProdutoPedidoFornecedorListViewHolderBase vh) {
        SharedPreferences pref = getActivity().getSharedPreferences("br.com.lojadigicom.repcom", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putInt(Constantes.PRODUTO_PEDIDO_FORNECEDOR_POSICAO, vh.getAdapterPosition());
        edt.commit();
    }
    private void recuperaPosicao() {
        SharedPreferences pref = getActivity().getSharedPreferences("br.com.lojadigicom.repcom", Context.MODE_PRIVATE);
        mPosition = pref.getInt(Constantes.PRODUTO_PEDIDO_FORNECEDOR_POSICAO,RecyclerView.NO_POSITION);
    }
	*/


    @Override
    public final void onActivityCreated(Bundle savedInstanceState) {
        // We hold for transition here just in-case the activity
        // needs to be re-created. In a standard return transition,
        // this doesn't actually make a difference.
        if ( mHoldForTransition ) {
            getActivity().supportPostponeEnterTransition();
        }
        getLoaderManager().initLoader(FORECAST_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

	

    @Override
    public final void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    	List<ProdutoPedidoFornecedor> lista = converteLista(data);
    	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Lista ProdutoPedidoFornecedor " + lista.size() + " itens");
        this.mAdapter.swapCursor(lista);
        if (mPosition != RecyclerView.NO_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore
            // to, do so now.
            mRecyclerView.smoothScrollToPosition(mPosition);
        }
        /*
        updateEmptyView();
        if ( data.getCount() == 0 ) {
            getActivity().supportStartPostponedEnterTransition();
        } else {
            mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    // Since we know we're going to get items, we keep the listener around until
                    // we see Children.
                    if (mRecyclerView.getChildCount() > 0) {
                        mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                        int itemPosition = mAdapter.getSelectedItemPosition();
                        if ( RecyclerView.NO_POSITION == itemPosition ) itemPosition = 0;
                        RecyclerView.ViewHolder vh = mRecyclerView.findViewHolderForAdapterPosition(itemPosition);
                        if ( null != vh && mAutoSelectView ) {
                            mAdapter.selectView( vh );
                        }
                        if ( mHoldForTransition ) {
                            getActivity().supportStartPostponedEnterTransition();
                        }
                        return true;
                    }
                    return false;
                }
            });
        }
        */

    }



    @Override
    public final void onDestroy() {
        super.onDestroy();
        if (null != mRecyclerView) {
            mRecyclerView.clearOnScrollListeners();
        }
    }

    @Override
    public final void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

	protected MontadorDaoI getMontador() {
        return ProdutoPedidoFornecedorContract.getMontador();
    }

    protected final List<ProdutoPedidoFornecedor> converteLista(Cursor data) {
        List lista = new ArrayList();
        try {
            lista = DBHelperBase.getListaSqlListaInterna(data, getMontador(), this);
        } catch (Exception e) {
            lista = new ArrayList();
        }
        return lista;
    }
}
