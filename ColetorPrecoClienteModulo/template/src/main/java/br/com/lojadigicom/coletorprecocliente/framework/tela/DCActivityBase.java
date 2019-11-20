
package br.com.lojadigicom.coletorprecocliente.framework.tela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;


public abstract class DCActivityBase extends AppCompatActivity {

	protected ResourceObj resourceObj = getLayoutResource();
	
	
	
	@Override
    protected final void onCreate(Bundle savedInstanceState) {
    	DCLog.d(DCLog.TRACE_DISPLAY,this,getClass().getSimpleName() + " (" + getLayoutResource().getNome() + ")" );
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource().getCodigo());
        onCreateComplemento(savedInstanceState);
         //criaToolbar("Lista de Espera",R.id.toolbar_lista_espera);
    }
	
	
    protected void criaToolbar(String texto, int idToolbar) {
        Toolbar toolbar = (Toolbar) this.findViewById(idToolbar);
        toolbar.setTitle(texto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
	protected abstract void onCreateComplemento(Bundle savedInstanceState);
	protected abstract ResourceObj getLayoutResource();
}