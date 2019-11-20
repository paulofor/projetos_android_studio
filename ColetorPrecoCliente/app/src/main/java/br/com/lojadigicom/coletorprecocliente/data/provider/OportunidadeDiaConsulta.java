
package  br.com.lojadigicom.coletorprecocliente.data.provider;


import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;
import br.com.lojadigicom.coletorprecocliente.modelo.montador.OportunidadeDiaMontador;

public final class OportunidadeDiaConsulta extends OportunidadeDiaProvider{

    public static MontadorDaoI getListaPorNaturezaProdutoMontador() {
        return new OportunidadeDiaMontador();
    }

}