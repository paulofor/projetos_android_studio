package br.com.lojadigicom.naturarepcom.aplicacao;

import android.app.Application;

import br.com.lojadigicom.repcom.data.DatabaseConst;
import br.com.lojadigicom.repcom.data.contract.AplicacaoContract;
import br.com.lojadigicom.repcom.data.helper.AplicacaoDbHelper;

/**
 * Created by Paulo on 18/06/2016.
 */
public class AplicacaoImpl extends Application {

    public AplicacaoImpl() {
        super();
        AplicacaoContract contract = new ContractApp();
        AplicacaoContract.setAplicacaoContract(contract);
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);
    }
}
