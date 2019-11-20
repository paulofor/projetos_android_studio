
package br.com.lojadigicom.repcom.tela.base.activity.detalhe;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.KeyEvent;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import android.transition.Transition;

import br.com.lojadigicom.repcom.template.R;

import br.com.lojadigicom.repcom.data.contract.DispositivoUsuarioContract;
import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;
import br.com.lojadigicom.repcom.framework.data.DCCursorLoader;
import br.com.lojadigicom.repcom.framework.tela.ResourceObj;
import br.com.lojadigicom.repcom.modelo.DispositivoUsuario;
import br.com.lojadigicom.repcom.framework.data.MontadorDaoComposite;
import br.com.lojadigicom.repcom.tela.base.activity.Constantes;
import br.com.lojadigicom.repcom.framework.storage.DownloadImageTask;
import br.com.lojadigicom.repcom.framework.log.DCLog;
/*
O que precisa ter num Activity de Detalhe:

1- Imagem - recursos de usar imagem
2- Anima??o
3- Titulo Variavel (toolbar)  -> precisa do xml (copu/paste)
4- Uri (a query fica no consulta) -> passando um id
5- metodo que recebe o Id do objeto em detalhe. ( uso de loader )


O que todo activity vai precisar:

- xml de layout
- link com os objetos de tela


*/


public abstract class DispositivoUsuarioDetalheBaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, Transition.TransitionListener {

	String titulo = null;
    private static final int DETAIL_LOADER = 779 * 10;
    private long idDispositivoUsuario = 0;
    
    protected Toolbar toolbar = null;

	protected ResourceObj resourceObj = getLayoutDetalheResource();
  
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
	    DCLog.d(DCLog.TRACE_DISPLAY,this,getClass().getSimpleName() + " (" + getLayoutDetalheResource().getNome() + ")" );
        super.onCreate(savedInstanceState);
        setContentView(resourceObj.getCodigo());
	    SharedPreferences pref = getPreferences(0);
 		Bundle extra = getIntent().getExtras();
 		titulo = (extra!=null?extra.getString(Constantes.CHAVE_TOOLBAR):null);
   		try {
            this.idDispositivoUsuario = extra.getInt(Constantes.DISPOSITIVO_USUARIO_ID);
        } catch (Exception e) {
            idDispositivoUsuario = pref.getLong(this.getClass().getSimpleName(), 0);
        }
  
        if (idDispositivoUsuario==0) {
            throw new RuntimeException("Nao recebeu o IdDispositivoUsuario");
        }
        SharedPreferences.Editor edt = pref.edit();
        edt.putLong(this.getClass().getSimpleName(), idDispositivoUsuario);
        edt.commit();
        this.getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);
        toolbar = (Toolbar) findViewById(getIdToolbar());
        setSupportActionBar(toolbar);
        if (titulo!=null) toolbar.setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
        
        complementaOnCreate(idDispositivoUsuario);
        
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         	esconderElementosParaPosAnimacao();
            Transition t2 = getWindow().getSharedElementEnterTransition();
            t2.addListener(this);
        }
        preparaTransicao();

       
    }

	protected abstract void esconderElementosParaPosAnimacao();

    protected abstract ResourceObj getLayoutDetalheResource();

 	protected void preparaTransicao() {
    }

	
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = DispositivoUsuarioContract.buildDispositivoUsuarioUri(idDispositivoUsuario);
        uri = completaUri(uri);
        return new DCCursorLoader(this,uri,null,null,null,null);
    }
    // Alterando aqui vai precisar alterar o completaMontador.
    protected Uri completaUri(Uri uri) {
        return uri;
    }
    /*
	Exemplos:
	- Para obter objetos agregados:
	
	@Override
    protected Uri completaUri(Uri uri) {
        uri = uri.buildUpon().appendPath(DispositivoUsuarioContract.COM_COMPLEMENTO).build();
        uri = DispositivoUsuarioContract.adiciona<Objeto><Relacionamento>(uri);
        return uri;
    } 
	*/
	
	protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return montadorDao;
    }
     /*
	Exemplos:
	- Para obter objetos agregados:
	
	@Override
    protected MontadorDaoI completaMontador(MontadorDaoComposite montadorDao) {
        return DispositivoUsuarioContract.adicionaMontador<Objeto><Relacionamento>(montadorDao);
    }
	*/
    
   
    
    
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
	    data.moveToFirst();
	    // Se o cliente da activity passar no Intent o titulo 
	    // Nao sobrepor com o nome do campo de banco de dados.
	    ActionBar actionBar = this.getSupportActionBar();
	    // ActionBar pode nao existir
	    if (actionBar!=null) {
		    if (titulo==null) {
    	    	String nome = data.getString(getConstColunaNomeTitulo());
	    	    actionBar.setTitle(nome);
		    } else {
    	        // Nao entendi por que so funciona se colocar aqui
        	    actionBar.setTitle(titulo);
        	}
	    }    
        MontadorDaoI montadorDao = DispositivoUsuarioContract.getMontadorComposto();
        montadorDao = completaMontador((MontadorDaoComposite)montadorDao);
        DispositivoUsuario item = (DispositivoUsuario) montadorDao.getItem(data);
        onLoadObject(item);
    }
   
    
	protected abstract void onLoadObject(DispositivoUsuario item);

	protected abstract int getConstColunaNomeTitulo();

    protected abstract void complementaOnCreate(long idDispositivoUsuario);

    protected abstract int getIdToolbar();
    
    
    // Se nao for muito adequado passar paraa ExibeItemListaBaseActivity (18-11-2016 ate 18-01-2017)
    /*protected void imagemFromUrl(ImageView obj, String urlImagem) {
        new DownloadImageTask(obj).execute(urlImagem);
    }*/
    protected void carregaImagemCache(String urlImagem, ImageView viewImagem) {
        Glide.with(this)
                .load(urlImagem)
                .error(R.drawable.img_nao_disponivel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewImagem);
    }
    
    protected void criaBotaoBack() {
    	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    /*
    protected void criaToolbar(String texto, int idToolbar) {
        Toolbar toolbar = (Toolbar) this.findViewById(idToolbar);
        toolbar.setTitle(texto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            //finish();
            //Se nao for assim nao executa a animacao.
            this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    // Tem em varios lugares, pode depois ficar centralizada.
    // (28-11-2016) ate 28-01-2017
    protected final TextView getTextView(int id, String nome) {
        TextView saida = (TextView) findViewById(id);
        if (saida==null) throw new RuntimeException("TextView " + nome + " nao encontrado em " + this);
        return saida;
    }
    protected final ImageView getImageView(int id, String nome) {
        ImageView saida = (ImageView) findViewById(id);
        if (saida==null) throw new RuntimeException("ImageView " + nome + " nao encontrado em " + this);
        return saida;
    }
    protected final RatingBar getRatingBar(int id, String nome) {
        RatingBar saida = (RatingBar) findViewById(id);
        if (saida==null) throw new RuntimeException("RatingBar " + nome + " nao encontrado em " + this);
        return saida;
    }
    
    
    // Transacao de SharedElment - Interface: Transition.TransitionListener
    @Override
    public void onTransitionStart(Transition transition) {
    }
    @Override
    public void onTransitionCancel(Transition transition) {
    }
    @Override
    public void onTransitionPause(Transition transition) {
    }
    @Override
    public void onTransitionResume(Transition transition) {
    }
    
}