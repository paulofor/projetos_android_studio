
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

import br.com.lojadigicom.repcom.data.contract.ProdutoPedidoFornecedorContract;
import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;
import br.com.lojadigicom.repcom.framework.data.DCCursorLoader;
import br.com.lojadigicom.repcom.framework.tela.ResourceObj;
import br.com.lojadigicom.repcom.modelo.ProdutoPedidoFornecedor;
import br.com.lojadigicom.repcom.framework.data.MontadorDaoComposite;


public abstract class ProdutoPedidoFornecedorDetalheBaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DETAIL_LOADER = 0;
    private long idProdutoPedidoFornecedor = 0;
    
    protected Toolbar toolbar = null;

	protected ResourceObj resourceObj = getLayoutDetalheResource();
  
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resourceObj.getCodigo());
	    SharedPreferences pref = getPreferences(0);
 		Bundle extra = getIntent().getExtras();
   		try {
            this.idProdutoPedidoFornecedor = extra.getInt(Constantes.PRODUTO_PEDIDO_FORNECEDOR_ID);
        } catch (Exception e) {
            idProdutoPedidoFornecedor = pref.getLong(this.getClass().getSimpleName(), 0);
        }
  
        if (idProdutoPedidoFornecedor==0) {
            throw new RuntimeException("Nao recebeu o IdProdutoPedidoFornecedor");
        }
        SharedPreferences.Editor edt = pref.edit();
        edt.putLong(this.getClass().getSimpleName(), idProdutoPedidoFornecedor);
        edt.commit();
        this.getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);
        toolbar = (Toolbar) findViewById(getIdToolbar());
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); Por causa da anima??o
        
        complementaOnCreate(idProdutoPedidoFornecedor);
        preparaTransicao();

       
    }

    protected abstract ResourceObj getLayoutDetalheResource();

 	protected void preparaTransicao() {
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = ProdutoPedidoFornecedorContract.buildProdutoPedidoFornecedorUri(idProdutoPedidoFornecedor);
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
        
        MontadorDaoI montadorDao = ProdutoPedidoFornecedorContract.getMontadorComposto();
        montadorDao = completaMontador((MontadorDaoComposite)montadorDao);
        ProdutoPedidoFornecedor item = (ProdutoPedidoFornecedor) montadorDao.getItem(data);
        onLoadObject(item);
    }
    protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return montadorDao;
    }
    
	protected abstract void onLoadObject(ProdutoPedidoFornecedor item);

	protected abstract int getConstColunaNomeTitulo();

    protected abstract void complementaOnCreate(long idProdutoPedidoFornecedor);

    protected abstract int getIdToolbar();
}