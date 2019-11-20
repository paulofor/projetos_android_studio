package br.com.lojadigicom.repcommodulo.aplicacao;

import android.app.Application;

import br.com.lojadigicom.repcom.data.contract.AplicacaoContract;
import br.com.lojadigicom.repcommodulo.R;


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
