package lojadigicom.com.br.capitalexternomodulo.app;

import android.app.Application;

import br.com.lojadigicom.capitalexterno.data.DatabaseConst;
import br.com.lojadigicom.capitalexterno.data.contract.AplicacaoContract;
import br.com.lojadigicom.capitalexterno.data.helper.AplicacaoDbHelper;
import br.com.lojadigicom.capitalexterno.framework.tela.DCAplicacao;
import br.com.lojadigicom.capitalexterno.sinc.Sincronizador;


/**
 * Created by Paulo on 18/06/2016.
 */
public class AplicacaoImpl extends DCAplicacao {


    public AplicacaoImpl() {
        super();
        AplicacaoContract contract = new ContractApp(this);
        AplicacaoContract.setAplicacaoContract(contract);
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);
        //Sincronizador.setCodigoAplicacao("1cf58ibzr4c9zie9qffu");
    }


}
