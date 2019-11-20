package br.com.lojadigicom.repcommodulo.aplicacao;


import android.app.Application;

import br.com.lojadigicom.repcom.data.DatabaseConst;
import br.com.lojadigicom.repcom.data.contract.AplicacaoContract;
import br.com.lojadigicom.repcom.data.helper.AplicacaoDbHelper;
import br.com.lojadigicom.repcom.framework.faturamento.ConstantesBilling;
import br.com.lojadigicom.repcom.framework.fcm.DCFirebaseMessagingService;
import br.com.lojadigicom.repcom.framework.fcm.DCNotificacaoParam;
import br.com.lojadigicom.repcom.framework.tela.DCAplicacao;
import br.com.lojadigicom.repcommodulo.R;


/**
 * Created by Paulo on 27/09/2016.
 */

public class RepcomApplication extends DCAplicacao {

    public RepcomApplication() {
        super();
        AplicacaoContract contract = new ContractApp(this);
        AplicacaoContract.setAplicacaoContract(contract);
        AplicacaoContract.setCodigoAplicacaoSinc("1ksvwkdrk2t2fhrohjbi");
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);

        DCNotificacaoParam notificaco = new NotificacaoParam(MainActivity.class,"Administração Natura",this);
        DCFirebaseMessagingService.setNotificador(notificaco);

    }

    @Override
    public ConstantesBilling getBilling() {
        return null;
    }

    @Override
    public String getLinkFacebook() {
        return "https://play.google.com/store/apps/details?id=br.com.lojadigicom.olhonoprecosapato";
    }

    @Override
    public Class activityInicio() {
        return MainActivity.class;
    }

    @Override
    protected ConstantesBilling criaBilling() {
        return new ConstantesBillingApp();
    }

    //private void  inicializaSincronizador() {
    //    Sincronizador sinc = new Sincronizador();
    //    DCFirebaseMessagingService.setSincronizador(sinc);
    //    DCFirebaseInstanceIDService.setSincronizador(sinc);
    //}


}
