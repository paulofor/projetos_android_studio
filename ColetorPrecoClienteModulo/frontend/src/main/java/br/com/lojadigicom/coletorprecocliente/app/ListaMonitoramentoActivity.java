package br.com.lojadigicom.coletorprecocliente.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCActivityBase;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;

public class ListaMonitoramentoActivity extends DCActivityBase {


    @Override
    protected void onCreateComplemento(Bundle savedInstanceState) {
        criaToolbar(this.getResources().getString(R.string.label_monitorando),R.id.toolbar_lista_monitoramento);
    }

    @Override
    protected ResourceObj getLayoutResource() {
        return new ResourceObj(R.layout.activity_lista_monitoramento,"R.layout.activity_lista_monitoramento");
    }
}
