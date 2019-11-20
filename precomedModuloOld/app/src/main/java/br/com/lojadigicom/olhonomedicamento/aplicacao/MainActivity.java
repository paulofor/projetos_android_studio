package br.com.lojadigicom.olhonomedicamento.aplicacao;

import br.com.lojadigicom.olhonoprecosapato.R;
import br.com.lojadigicom.tela.MainActivityBaseGcm;

public class MainActivity extends MainActivityBaseGcm {



    @Override
    protected int getToolbarResource() {
        return R.id.toolbar_principal;
    }
    @Override
    protected String getTitulo() {
        return "Olho no preço - Sapatos";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }


}
