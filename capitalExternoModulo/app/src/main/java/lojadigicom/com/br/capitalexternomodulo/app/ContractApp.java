package lojadigicom.com.br.capitalexternomodulo.app;

import android.app.Application;

import br.com.lojadigicom.capitalexterno.data.contract.AplicacaoContract;
import lojadigicom.com.br.capitalexternomodulo.R;


/**
 * Created by Paulo on 18/06/2016.
 */
public class ContractApp extends AplicacaoContract {

    private Application application = null;

    public ContractApp(Application app) {
        application = app;
    }

    @Override
    public String getContentAuthority() {
        return application.getString(R.string.content_authority);
        //return "br.com.lojadigicom.coletorprecocliente";
    }
}
