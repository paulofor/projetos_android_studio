package br.com.lojadigicom.coletorprecocliente.data.provider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;

/**
 * Created by Paulo on 05/04/2016.
 */
public class ProdutoClienteConsulta extends ProdutoClienteProvider {



    @Override
    protected void notificaOutrasUri(ContentResolver resolver) {

    }


    @Override
    protected String queryListaNaoEscolhido(String param) {
        return null;
    }

    // Ordenação atualmente pelo id do produto
    @Override
    protected String queryObtemProximoNaoEscolhido(String param) {
        String params[] = param.split("&");
        String dados[] = params[0].split("=");
        String filtro = ProdutoClienteContract.COLUNA_ID_NATUREZA_PRODUTO_RA + " = " + dados[1];
        String sql = "select " + ProdutoClienteContract.camposOrdenados() + " , " +
                InteresseProdutoContract.camposOrdenados() +
                " from " + ProdutoClienteContract.TABLE_NAME +
                ProdutoClienteContract.outerJoinInteresseProduto_Possui() +
                " where " + ProdutoClienteContract.COLUNA_ID_PRODUTO_CLIENTE +
                " not in (select " + InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA + " from " +
                InteresseProdutoContract.TABLE_NAME +
                " where " + InteresseProdutoContract.COLUNA_VISUALIZACAO_CONCLUIDA + " ) " +
                " and " + filtro +
                " order by " + ProdutoClienteContract.COLUNA_ID_PRODUTO_CLIENTE;

        return sql;
    }
}
