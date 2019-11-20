package br.com.lojadigicom.olhonoprecomodulo.app;

import br.com.lojadigicom.coletorprecocliente.data.DatabaseConst;
import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import br.com.lojadigicom.coletorprecocliente.framework.faturamento.ConstantesBilling;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCFirebaseInstanceIDService;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCFirebaseMessagingService;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCNotificacaoParam;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCAplicacao;
import br.com.lojadigicom.coletorprecocliente.sync.Sincronizador;
import br.com.lojadigicom.olhonoprecomodulo.R;
import br.com.lojadigicom.olhonoprecosapato.MainActivity;

/**
 * Created by Paulo on 18/06/2016.
 */
public class AplicacaoImpl extends DCAplicacao {




    public AplicacaoImpl() {
        super();
        AplicacaoContract contract = new ContractApp(this);
        AplicacaoContract.setAplicacaoContract(contract);
        AplicacaoContract.setCodigoAplicacaoSinc("1ksvwkdrk2t2fhrohjbi");
        DatabaseConst db = new DatabaseApp();
        AplicacaoDbHelper.setDatabaseConst(db);
        DCNotificacaoParam notificaco = new NotificacaoParam("Olho no preço",this);
        DCFirebaseMessagingService.setNotificador(notificaco);


    }

    private void  inicializaSincronizador() {
        Sincronizador sinc = new Sincronizador(this);
        DCFirebaseMessagingService.setSincronizador(sinc);
        DCFirebaseInstanceIDService.setSincronizador(sinc);
        AplicacaoDbHelper.setSincronizador(sinc);
    }

    @Override
    public String getLinkFacebook() {
        return "http://www.lojadigicom.com.br/redirect.html";
    }

    @Override
    public Class activityInicio() {
        return MainActivity.class;
    }

    @Override
    public ConstantesBilling criaBilling() {
        return new ConstantesBillingApp();
    }


}
