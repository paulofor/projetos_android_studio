
package br.com.lojadigicom.coletorprecocliente.tela.base.activity;

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

import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeDiaContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.OportunidadeDia;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;


public abstract class OportunidadeDiaDetalheBaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DETAIL_LOADER = 0;
    private long idOportunidadeDia = 0;
    
    protected Toolbar toolbar = null;

	protected ResourceObj resourceObj = getLayoutDetalheResource();
  
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resourceObj.getCodigo());
	    SharedPreferences pref = getPreferences(0);
 		Bundle extra = getIntent().getExtras();
   		try {
            this.idOportunidadeDia = extra.getInt(Constantes.OPORTUNIDADE_DIA_ID);
        } catch (Exception e) {
            idOportunidadeDia = pref.getLong(this.getClass().getSimpleName(), 0);
        }
  
        if (idOportunidadeDia==0) {
            throw new RuntimeException("Nao recebeu o IdOportunidadeDia");
        }
        SharedPreferences.Editor edt = pref.edit();
        edt.putLong(this.getClass().getSimpleName(), idOportunidadeDia);
        edt.commit();
        this.getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);
        toolbar = (Toolbar) findViewById(getIdToolbar());
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); Por causa da anima??o
        
        complementaOnCreate(idOportunidadeDia);
        preparaTransicao();

       
    }

    protected abstract ResourceObj getLayoutDetalheResource();

 	protected void preparaTransicao() {
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = OportunidadeDiaContract.buildOportunidadeDiaUri(idOportunidadeDia);
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
        
        MontadorDaoI montadorDao = OportunidadeDiaContract.getMontadorComposto();
        montadorDao = completaMontador((MontadorDaoComposite)montadorDao);
        OportunidadeDia item = (OportunidadeDia) montadorDao.getItem(data);
        onLoadObject(item);
    }
    protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return montadorDao;
    }
    
	protected abstract void onLoadObject(OportunidadeDia item);

	protected abstract int getConstColunaNomeTitulo();

    protected abstract void complementaOnCreate(long idOportunidadeDia);

    protected abstract int getIdToolbar();
}