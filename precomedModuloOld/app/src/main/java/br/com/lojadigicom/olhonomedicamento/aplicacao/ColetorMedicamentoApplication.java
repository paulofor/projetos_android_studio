package br.com.lojadigicom.olhonomedicamento.aplicacao;


import android.app.Application;

import br.com.lojadigicom.precomed.data.DatabaseConst;
import br.com.lojadigicom.precomed.data.contract.AplicacaoContract;
import br.com.lojadigicom.precomed.data.helper.AplicacaoDbHelper;
import br.com.lojadigicom.precomed.framework.fcm.DCFirebaseMessagingService;

/**
 * Created by Paulo on 27/09/2016.
 */

public class ColetorMedicamentoApplication extends Application {

    public ColetorMedicamentoApplication() {
        super();
        AplicacaoContract contract = new ContractApp(this);
        AplicacaoContract.setAplicacaoContract(contract);
        AplicacaoContract.setCodigoAplicacaoSinc("1ksvwkdrk2t2fhrohjbi");
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);
        inicializaSincronizador();
    }

    private void  inicializaSincronizador() {
        Sincronizador sinc = new Sincronizador();
        sinc.setCodigoAplicacao("1ksvwkdrk2t2fhrohjbi");;
        DCFirebaseMessagingService.setSincronizador(sinc);
    }
}
