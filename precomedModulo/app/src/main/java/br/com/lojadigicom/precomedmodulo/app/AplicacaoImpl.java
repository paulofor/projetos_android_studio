package br.com.lojadigicom.precomedmodulo.app;

import android.app.Application;

import br.com.lojadigicom.precomed.data.DatabaseConst;
import br.com.lojadigicom.precomed.data.contract.AplicacaoContract;
import br.com.lojadigicom.precomed.data.helper.AplicacaoDbHelper;


/**
 * Created by Paulo on 18/06/2016.
 */
public class AplicacaoImpl extends Application {


    public AplicacaoImpl() {
        super();
        AplicacaoContract contract = new ContractApp(this);
        AplicacaoContract.setAplicacaoContract(contract);
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);
        //Sincronizador.setCodigoAplicacao("1cf58ibzr4c9zie9qffu");
    }


}
