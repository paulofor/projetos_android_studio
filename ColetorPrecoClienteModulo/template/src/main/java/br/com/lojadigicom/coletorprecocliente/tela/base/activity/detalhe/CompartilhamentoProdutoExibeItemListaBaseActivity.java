
package br.com.lojadigicom.coletorprecocliente.tela.base.activity.detalhe;

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

import android.widget.ImageView;
import br.com.lojadigicom.coletorprecocliente.data.contract.CompartilhamentoProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.CompartilhamentoProduto;
import br.com.lojadigicom.coletorprecocliente.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.coletorprecocliente.framework.storage.DownloadImageTask;



public abstract class CompartilhamentoProdutoExibeItemListaBaseActivity extends CompartilhamentoProdutoDetalheBaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	
}