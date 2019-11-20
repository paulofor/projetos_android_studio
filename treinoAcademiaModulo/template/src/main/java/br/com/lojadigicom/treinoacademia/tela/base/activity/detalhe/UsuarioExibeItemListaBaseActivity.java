
package br.com.lojadigicom.treinoacademia.tela.base.activity.detalhe;

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
import br.com.lojadigicom.treinoacademia.data.contract.UsuarioContract;
import br.com.lojadigicom.treinoacademia.framework.dao.MontadorDaoI;
import br.com.lojadigicom.treinoacademia.framework.data.DCCursorLoader;
import br.com.lojadigicom.treinoacademia.framework.tela.ResourceObj;
import br.com.lojadigicom.treinoacademia.modelo.Usuario;
import br.com.lojadigicom.treinoacademia.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.treinoacademia.tela.base.activity.Constantes;
import br.com.lojadigicom.treinoacademia.framework.storage.DownloadImageTask;



public abstract class UsuarioExibeItemListaBaseActivity extends UsuarioDetalheBaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	
}