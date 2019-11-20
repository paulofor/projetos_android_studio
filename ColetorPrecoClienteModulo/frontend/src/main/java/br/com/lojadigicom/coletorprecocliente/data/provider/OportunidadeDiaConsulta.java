
package  br.com.lojadigicom.coletorprecocliente.data.provider;


import android.content.ContentResolver;

import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.OportunidadeDiaMontador;

public final class OportunidadeDiaConsulta extends OportunidadeDiaProvider{

    @Override
    protected void notificaOutrasUri(ContentResolver resolver) {

    }

    public static MontadorDaoI getListaPorNaturezaProdutoMontador() {
        return new OportunidadeDiaMontador();
    }

}