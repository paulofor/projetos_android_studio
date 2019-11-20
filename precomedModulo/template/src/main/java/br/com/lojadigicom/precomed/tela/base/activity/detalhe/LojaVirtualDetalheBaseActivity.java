
package br.com.lojadigicom.precomed.tela.base.activity.detalhe;

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

import br.com.lojadigicom.precomed.data.contract.LojaVirtualContract;
import br.com.lojadigicom.precomed.framework.dao.MontadorDaoI;
import br.com.lojadigicom.precomed.framework.data.DCCursorLoader;
import br.com.lojadigicom.precomed.framework.tela.ResourceObj;
import br.com.lojadigicom.precomed.modelo.LojaVirtual;
import br.com.lojadigicom.precomed.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.precomed.tela.base.activity.Constantes;

public abstract class LojaVirtualDetalheBaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	String titulo = null;
    private static final int DETAIL_LOADER = 0;
    private long idLojaVirtual = 0;
    
    protected Toolbar toolbar = null;

	protected ResourceObj resourceObj = getLayoutDetalheResource();
  
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resourceObj.getCodigo());
	    SharedPreferences pref = getPreferences(0);
 		Bundle extra = getIntent().getExtras();
 		titulo = (extra!=null?extra.getString(Constantes.CHAVE_TOOLBAR):null);
   		try {
            this.idLojaVirtual = extra.getInt(Constantes.LOJA_VIRTUAL_ID);
        } catch (Exception e) {
            idLojaVirtual = pref.getLong(this.getClass().getSimpleName(), 0);
        }
  
        if (idLojaVirtual==0) {
            throw new RuntimeException("Nao recebeu o IdLojaVirtual");
        }
        SharedPreferences.Editor edt = pref.edit();
        edt.putLong(this.getClass().getSimpleName(), idLojaVirtual);
        edt.commit();
        this.getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);
        toolbar = (Toolbar) findViewById(getIdToolbar());
        setSupportActionBar(toolbar);
        if (titulo!=null) toolbar.setTitle(titulo);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); Por causa da anima??o
        
        complementaOnCreate(idLojaVirtual);
        preparaTransicao();

       
    }

    protected abstract ResourceObj getLayoutDetalheResource();

 	protected void preparaTransicao() {
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = LojaVirtualContract.buildLojaVirtualUri(idLojaVirtual);
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
	    // Se o cliente da activity passar no Intent o titulo 
	    // Nao sobrepor com o nome do campo de banco de dados.
	    if (titulo==null) {
        	String nome = data.getString(getConstColunaNomeTitulo());
	        ActionBar actionBar = this.getSupportActionBar();
	        actionBar.setTitle(nome);
	    } else {
            // Nao entendi por que so funciona se colocar aqui
            ActionBar actionBar = this.getSupportActionBar();
            actionBar.setTitle(titulo);
        }
	        
        
        MontadorDaoI montadorDao = LojaVirtualContract.getMontadorComposto();
        montadorDao = completaMontador((MontadorDaoComposite)montadorDao);
        LojaVirtual item = (LojaVirtual) montadorDao.getItem(data);
        onLoadObject(item);
    }
    protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return montadorDao;
    }
    
	protected abstract void onLoadObject(LojaVirtual item);

	protected abstract int getConstColunaNomeTitulo();

    protected abstract void complementaOnCreate(long idLojaVirtual);

    protected abstract int getIdToolbar();
}