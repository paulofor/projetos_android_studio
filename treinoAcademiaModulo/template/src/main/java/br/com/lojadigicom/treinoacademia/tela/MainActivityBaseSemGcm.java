
package br.com.lojadigicom.treinoacademia.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import br.com.lojadigicom.treinoacademia.framework.tela.ResourceObj;
import br.com.lojadigicom.treinoacademia.template.R;



public abstract class MainActivityBaseSemGcm extends AppCompatActivity {

	@Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
