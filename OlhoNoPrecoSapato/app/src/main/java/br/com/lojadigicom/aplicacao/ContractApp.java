package br.com.lojadigicom.aplicacao;

import android.app.Application;

import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;

import br.com.lojadigicom.olhonoprecosapato.R;

/**
 * Created by Paulo on 28/09/2016.
 */
public class ContractApp extends AplicacaoContract {

    public ContractApp(Application app) {
        application = app;
    }

    @Override
    public String getContentAuthority() {
        return application.getString(R.string.content_authority);
    }
}
