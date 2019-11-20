
package br.com.lojadigicom.repcom.tela.base.activity.detalhe;

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
import br.com.lojadigicom.repcom.data.contract.VendaContract;
import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;
import br.com.lojadigicom.repcom.framework.data.DCCursorLoader;
import br.com.lojadigicom.repcom.framework.tela.ResourceObj;
import br.com.lojadigicom.repcom.modelo.Venda;
import br.com.lojadigicom.repcom.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.repcom.tela.base.activity.Constantes;
import br.com.lojadigicom.repcom.framework.storage.DownloadImageTask;



public abstract class VendaExibeItemListaBaseActivity extends VendaDetalheBaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	
}