
package br.com.lojadigicom.repcom.tela.base.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.content.SharedPreferences;

import br.com.lojadigicom.repcom.data.contract.PrecoProdutoContract;
import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;
import br.com.lojadigicom.repcom.framework.data.DCCursorLoader;
import br.com.lojadigicom.repcom.framework.tela.ResourceObj;
import br.com.lojadigicom.repcom.modelo.PrecoProduto;
import br.com.lojadigicom.repcom.framework.data.MontadorDaoComposite;


public abstract class PrecoProdutoDetalheBaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DETAIL_LOADER = 0;
    private long idPrecoProduto = 0;
    
    protected Toolbar toolbar = null;

	protected ResourceObj resourceObj = getLayoutDetalheResource();
  
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resourceObj.getCodigo());
	    SharedPreferences pref = getPreferences(0);
 		Bundle extra = getIntent().getExtras();
   		try {
            this.idPrecoProduto = extra.getInt(Constantes.PRECO_PRODUTO_ID);
        } catch (Exception e) {
            idPrecoProduto = pref.getLong(this.getClass().getSimpleName(), 0);
        }
  
        if (idPrecoProduto==0) {
            throw new RuntimeException("Nao recebeu o IdPrecoProduto");
        }
        SharedPreferences.Editor edt = pref.edit();
        edt.putLong(this.getClass().getSimpleName(), idPrecoProduto);
        edt.commit();
        this.getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);
        toolbar = (Toolbar) findViewById(getIdToolbar());
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); Por causa da anima??o
        
        complementaOnCreate(idPrecoProduto);
        preparaTransicao();

       
    }

    protected abstract ResourceObj getLayoutDetalheResource();

 	protected void preparaTransicao() {
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = PrecoProdutoContract.buildPrecoProdutoUri(idPrecoProduto);
        uri = completaUri(uri);
        return new DCCursorLoader(this,uri,null,null,null,null);
    }
    protected Uri completaUri(Uri uri) {
        return uri;
    }
    
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        String nome = data.getString(getConstColunaNomeTitulo());
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle(nome);
        
        MontadorDaoI montadorDao = PrecoProdutoContract.getMontadorComposto();
        montadorDao = completaMontador((MontadorDaoComposite)montadorDao);
        PrecoProduto item = (PrecoProduto) montadorDao.getItem(data);
        onLoadObject(item);
    }
    protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return montadorDao;
    }
    
	protected abstract void onLoadObject(PrecoProduto item);

	protected abstract int getConstColunaNomeTitulo();

    protected abstract void complementaOnCreate(long idPrecoProduto);

    protected abstract int getIdToolbar();
}