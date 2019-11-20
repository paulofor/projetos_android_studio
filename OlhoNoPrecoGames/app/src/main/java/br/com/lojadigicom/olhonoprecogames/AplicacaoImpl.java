package br.com.lojadigicom.olhonoprecogames;

import android.app.Application;


import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;
import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import br.com.lojadigicom.coletorprecocliente.sync.Sincronizador;

/**
 * Created by Paulo on 17/06/2016.
 */
public class AplicacaoImpl extends Application {

    public AplicacaoImpl() {
        super();
        AplicacaoContract contract = new ContractImpl(this);
        AplicacaoContract.setAplicacaoContract(contract);
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);
        Sincronizador.setCodigoAplicacao("1cf58ibzr4c9zie9qffu");
    }
}
