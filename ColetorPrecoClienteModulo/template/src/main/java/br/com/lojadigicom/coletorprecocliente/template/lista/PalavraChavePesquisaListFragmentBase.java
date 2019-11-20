
package  br.com.lojadigicom.coletorprecocliente.template.lista;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Build;
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
import android.support.v4.util.Pair;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.framework.tela.DCAplicacao;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.modelo.PalavraChavePesquisa;
import br.com.lojadigicom.coletorprecocliente.data.contract.PalavraChavePesquisaContract;
import br.com.lojadigicom.coletorprecocliente.template.R;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.framework.dao.DBHelperBase;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.tela.SimpleDividerItemDecoration;

public abstract class PalavraChavePesquisaListFragmentBase extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView mRecyclerView;
    private PalavraChavePesquisaListAdapterBase mAdapter;

    private int mPosition = RecyclerView.NO_POSITION;
    private int mChoiceMode;
    private static final String SELECTED_KEY = "selected_position";
    private static final int FORECAST_LOADER = 890;
    private boolean mHoldForTransition;

	protected ResourceObj resourceObj = getLayoutListaResource();
 	//protected int getIdLayoutLista() { return R.layout.lista_padrao; }
    protected int getIdRecLista() { return R.id.rec_lista_padrao; }
    protected int getIdRecListaEmpty() { return R.id.txt_lista_padrao_vazia; }


	public PalavraChavePesquisaListFragmentBase() {
    }


	private final void onItemSelected(int idItemLista, PalavraChavePesquisaListViewHolderBase vh) {
        Intent intent = new Intent(getActivity().getApplicationContext(), getOnClickLista());
        DCLog.d("OnClick", this, "Item selecionado ID:" + idItemLista);
        intent.putExtra(Constantes.PALAVRA_CHAVE_PESQUISA_ID,idItemLista);
        if (idItemLista==0) {
            throw new RuntimeException("Id igual a zero");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           /* ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this.getActivity(),
                    Pair.create((View)holder.mNomeProduto, "trNomeProduto"),
                    Pair.create((View)holder.mPrecoAtual, "trPrecoAtual"));*/
            ArrayList<Pair<View,String>> lista = new ArrayList<Pair<View,String>>();
            montaAminacao(vh,lista);
            Pair[] listaPar = lista.toArray(new Pair[lista.size()]);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this.getActivity(),listaPar);
           this.startActivity(intent,options.toBundle());
        } else {
            this.startActivity(intent);
        }
    }

    protected abstract Class getOnClickLista();
    protected abstract void montaAminacao(PalavraChavePesquisaListViewHolderBase vh, ArrayList<Pair<View,String>> lista);
	

    

	protected ResourceObj getLayoutListaResource() {
        return new ResourceObj(R.layout.lista_padrao,"R.layout.lista_padrao");
    }
   


    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DCLog.d(DCLog.DISPLAY,this,this.getClass().getSimpleName());
	    DCLog.d(DCLog.TRACE_DISPLAY,this,getClass().getSimpleName() + " (" + getLayoutListaResource().getNome() + ")" );
  		//recuperaPosicao();
        View rootView = inflater.inflate(resourceObj.getCodigo(), container, false);

        // Get a reference to the RecyclerView, and attach this adapter to it.
        mRecyclerView = (RecyclerView) rootView.findViewById(getIdRecLista());

        // Set the layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getApplicationContext()
        ));
        
        
		TextView emptyView = (TextView) rootView.findViewById(getIdRecListaEmpty());
        emptyView.setText(getTextoListaVazia());

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

		DCLog.d(DCLog.TRACE_LISTA,this,"Inicio");
        // The ForecastAdapter will take data from a source and
        // use it to populate the RecyclerView it's attached to.
        mAdapter = getListAdapter(this.getActivity(), new PalavraChavePesquisaListAdapterBase.PalavraChavePesquisaListAdapterOnClickHandler() {
            @Override
            public void onClick(int idItemLista, PalavraChavePesquisaListViewHolderBase vh) {
                //String locationSetting = Utility.getPreferredLocation(getActivity());
                onItemSelected(idItemLista, vh);
                //registraPosicao(vh);
                mPosition = vh.getAdapterPosition();
            }
        }, emptyView, mChoiceMode);
		DCLog.d(DCLog.TRACE_LISTA,this,"Adapter: " + mAdapter.getClass().getSimpleName());
		DCLog.d(DCLog.TRACE_DISPLAY,this,"(Lista)" + mAdapter.getClass().getSimpleName() + "(" + mAdapter.getLayoutItem() + ")");
		
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

		SharedPreferences pref = getActivity().getSharedPreferences("br.com.lojadigicom.coletorprecocliente", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        inicializaListaInferior(edt);
        edt.commit();


        return rootView;
    }

	protected abstract String getTextoListaVazia();

	protected abstract void inicializaListaInferior(SharedPreferences.Editor edt);
    protected abstract PalavraChavePesquisaListAdapterBase getListAdapter(FragmentActivity activity, PalavraChavePesquisaListAdapterBase.PalavraChavePesquisaListAdapterOnClickHandler onClickHandler, View emptyView, int mChoiceMode);

	/*
 	public void registraPosicao(PalavraChavePesquisaListViewHolderBase vh) {
        SharedPreferences pref = getActivity().getSharedPreferences("br.com.lojadigicom.coletorprecocliente", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putInt(Constantes.PALAVRA_CHAVE_PESQUISA_POSICAO, vh.getAdapterPosition());
        edt.commit();
    }
    private void recuperaPosicao() {
        SharedPreferences pref = getActivity().getSharedPreferences("br.com.lojadigicom.coletorprecocliente", Context.MODE_PRIVATE);
        mPosition = pref.getInt(Constantes.PALAVRA_CHAVE_PESQUISA_POSICAO,RecyclerView.NO_POSITION);
    }
	*/


    @Override
    public final void onActivityCreated(Bundle savedInstanceState) {
    	if (!(this.getActivity().getApplication() instanceof DCAplicacao)) {
            throw new RuntimeException("Precisa colocar no tag aplication do manifest: android:name=\".app.AplicacaoImpl\"");
        }
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
    public final Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = getUri();
        DCLog.d(DCLog.TRACE_LISTA,this,"Uri: " + uri + " Ordenacao: " + getColunaOrdenacao());
        DCLog.d(DCLog.TRACE_LISTA,this,"Montador: " + getMontador().getClass().getSimpleName());
        return new DCCursorLoader(getActivity(),uri,null,null,null,getColunaOrdenacao(),getMontador());
    }
    protected abstract Uri getUri();
    protected abstract String getColunaOrdenacao(); 

    @Override
    public final void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    	List<PalavraChavePesquisa> lista = converteLista(data);
    	DCLog.d(DCLog.OPERACAO_DB_TELA,this,"Lista PalavraChavePesquisa " + lista.size() + " itens");
    	DCLog.d(DCLog.TRACE_LISTA,this,"List<PalavraChavePesquisa> : " + lista.size() + " itens");
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
        return PalavraChavePesquisaContract.getMontador();
    }

    protected final List<PalavraChavePesquisa> converteLista(Cursor data) {
        List lista = new ArrayList();
        try {
            lista = DBHelperBase.getListaSqlListaInterna(data, getMontador(), this);
        } catch (Exception e) {
            lista = new ArrayList();
        }
        return lista;
    }
    
    // ComChaveEstrangeira
  	
	protected long mIdUsuario;
	public final void setIdUsuario(long id) {
		mIdUsuario = id;
	}
	
	protected long mIdNaturezaProduto;
	public final void setIdNaturezaProduto(long id) {
		mIdNaturezaProduto = id;
	}
	
}
