
package br.com.lojadigicom.coletorprecocliente.tela.base.activity.lista;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;


import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.coletorprecocliente.framework.dao.DBHelperBase;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.AppProduto;


public abstract class AppProdutoListaBaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	
	private static final int DETAIL_LOADER = (900 * 10) + 1;
	
 	@Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onCreateComplemento(savedInstanceState);
        this.getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);
    }
    protected abstract void onCreateComplemento(Bundle savedInstanceState);
    protected abstract int getContentView();
    
    @Override
    public final Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = getUri();
        DCLog.d(DCLog.TRACE_LISTA,this,"Uri: " + uri + " Ordenacao: " + getColunaOrdenacao());
        DCLog.d(DCLog.TRACE_LISTA,this,"Montador: " + getMontador().getClass().getSimpleName());
        return new DCCursorLoader(this,uri,null,null,null,getColunaOrdenacao(),getMontador());
    }
    protected abstract Uri getUri();
    protected abstract MontadorDaoI getMontador();
    protected abstract String getColunaOrdenacao(); 

    @Override
    public final void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<AppProduto> lista = converteLista(data);
       	DCLog.d(DCLog.TRACE_LISTA,this,"List<AppProduto> : " + lista.size() + " itens");
        trataLista(lista);
    }
    protected abstract void trataLista(List<AppProduto> lista);

    @Override
    public final void onLoaderReset(Loader<Cursor> loader) {

    }
    private final List<AppProduto> converteLista(Cursor data) {
        List lista = new ArrayList();
        try {
            lista = DBHelperBase.getListaSqlListaInterna(data, getMontador(), this);
        } catch (Exception e) {
            lista = new ArrayList();
        }
        return lista;
    }
    
}