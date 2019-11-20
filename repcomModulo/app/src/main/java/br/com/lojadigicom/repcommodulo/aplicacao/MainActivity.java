package br.com.lojadigicom.repcommodulo.aplicacao;



import android.content.Intent;
import android.view.MenuItem;

import br.com.lojadigicom.repcom.tela.MainActivityBaseGcm;
import br.com.lojadigicom.repcommodulo.R;


public class MainActivity extends MainActivityBaseGcm {



    @Override
    protected int getToolbarResource() {
        return R.id.toolbar_principal;
    }
    @Override
    protected String getTitulo() {
        return this.getResources().getString(R.string.titulo_aplicacao);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //if (id == R.id.menuEspera) {
        //    Intent mIntent = new Intent(this, ListaEsperaActivity.class);
        //    startActivity(mIntent);
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
