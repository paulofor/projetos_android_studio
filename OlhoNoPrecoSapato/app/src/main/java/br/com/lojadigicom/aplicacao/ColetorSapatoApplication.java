package br.com.lojadigicom.aplicacao;

import android.app.Application;

import br.com.lojadigicom.aplicacao.ContractApp;
import br.com.lojadigicom.aplicacao.DatabaseApp;
import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;
import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCFirebaseInstanceIDService;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCFirebaseMessagingService;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCNotificacaoParam;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCAplicacao;
import br.com.lojadigicom.coletorprecocliente.sync.Sincronizador;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCSincronizador;
import br.com.lojadigicom.olhonoprecosapato.R;

/**
 * Created by Paulo on 27/09/2016.
 */

public class ColetorSapatoApplication extends DCAplicacao {



    public String getLinkFacebook() {
        return "https://play.google.com/store/apps/details?id=br.com.lojadigicom.olhonoprecosapato";
    }

    public ColetorSapatoApplication() {
        super();
        AplicacaoContract contract = new ContractApp(this);
        AplicacaoContract.setAplicacaoContract(contract);
        // Id da Aplicação no Banco Digicom
        AplicacaoContract.setCodigoAplicacaoSinc("1ksvwkdrk2t2fhrohjbi");
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);
        inicializaSincronizador();
        DCNotificacaoParam notificaco = new NotificacaoParam(MainActivity.class,"Olho no preço");
        DCFirebaseMessagingService.setNotificador(notificaco);
    }

    private void  inicializaSincronizador() {
        Sincronizador sinc = new Sincronizador(this);
        DCFirebaseMessagingService.setSincronizador(sinc);
        DCFirebaseInstanceIDService.setSincronizador(sinc);
        AplicacaoDbHelper.setSincronizador(sinc);
    }

    @Override
    public Class activityInicio() {
        return MainActivity.class;
    }
}
