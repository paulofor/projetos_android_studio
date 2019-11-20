package br.com.lojadigicom.coletorprecocliente.aplicacao;

import android.app.Application;

import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;
import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.DispositivoUsuarioContract;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import br.com.lojadigicom.coletorprecocliente.sync.Sincronizador;


/**
 * Created by Paulo on 18/06/2016.
 */
public class AplicacaoColetorPrecoCliente extends Application{


    public AplicacaoColetorPrecoCliente() {
        super();
        AplicacaoContract contract = new ContractApp();
        AplicacaoContract.setAplicacaoContract(contract);
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);
        Sincronizador.setCodigoAplicacao("1cf58ibzr4c9zie9qffu");
    }

    @Override
    public void onCreate() {

        super.onCreate();

    }
}
