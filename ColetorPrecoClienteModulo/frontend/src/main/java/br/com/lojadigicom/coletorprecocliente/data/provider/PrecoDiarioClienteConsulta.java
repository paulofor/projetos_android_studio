package br.com.lojadigicom.coletorprecocliente.data.provider;

import android.content.ContentResolver;

import br.com.lojadigicom.coletorprecocliente.data.contract.PrecoDiarioClienteContract;
import br.com.lojadigicom.coletorprecocliente.modelo.PrecoDiarioCliente;

/**
 * Created by Paulo on 16/12/2016.
 */
public class PrecoDiarioClienteConsulta extends PrecoDiarioClienteProvider {
    @Override
    protected String queryQuantidadePorProduto(String param) {
        String params[] = param.split("&");
        String dados[] = params[0].split("=");
        String idProduto = dados[1];
        String sql = "select count(*) as qtde from " + PrecoDiarioClienteContract.TABLE_NAME +
                " where " + PrecoDiarioClienteContract.COLUNA_ID_PRODUTO_CLIENTE_PA + " = " + idProduto;
        return sql;
    }

    @Override
    protected void notificaOutrasUri(ContentResolver resolver) {

    }
}
