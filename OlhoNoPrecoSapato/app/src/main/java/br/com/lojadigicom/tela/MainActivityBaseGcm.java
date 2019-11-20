
package br.com.lojadigicom.tela;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.UsuarioSincronismo;
import br.com.lojadigicom.sync.AsyncTaskUtil;


public abstract class MainActivityBaseGcm extends AppCompatActivity {

	@Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AsyncTaskUtil teste = new AsyncTaskUtil();
        //teste.execute(this);
        setContentView(getLayoutResource());
        inicializaToolbar();
    }

   	private void inicializaToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(getToolbarResource());
        toolbar.setTitle(getTitulo());
        setSupportActionBar(toolbar);
    }




    protected abstract int getLayoutResource();
    protected abstract int getToolbarResource();
    protected abstract String getTitulo();

}
