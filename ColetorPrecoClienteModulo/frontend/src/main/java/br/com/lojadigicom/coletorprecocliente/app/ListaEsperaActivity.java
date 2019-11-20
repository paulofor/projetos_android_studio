package br.com.lojadigicom.coletorprecocliente.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCActivityBase;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;

public class ListaEsperaActivity extends DCActivityBase {



    @Override
    protected void onCreateComplemento(Bundle savedInstanceState) {
        criaToolbar(getResources().getString(R.string.label_esperando),R.id.toolbar_lista_espera);
    }

    @Override
    protected ResourceObj getLayoutResource() {
        return new ResourceObj(R.layout.activity_lista_espera,"R.layout.activity_lista_espera");
    }


}
