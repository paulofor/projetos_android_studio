
package br.com.lojadigicom.repcom.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.framework.tela.ResourceObj;
import br.com.lojadigicom.repcom.template.R;



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
