package br.com.lojadigicom.olhonoprecogames;

import android.app.Application;

import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;

/**
 * Created by Paulo on 17/06/2016.
 */
public class ContractImpl extends AplicacaoContract {



    public ContractImpl(Application app) {
        application = app;
    }

    @Override
    public String getContentAuthority() {
        return application.getString(R.string.content_authority);
    }



}
