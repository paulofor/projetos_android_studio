package br.com.lojadigicom.coletorprecocliente.data.provider;

import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;

/**
 * Created by Paulo on 05/04/2016.
 */
public class ProdutoClienteConsulta extends ProdutoClienteProvider {



    @Override
    protected Cursor queryListaNaoEscolhido(Uri uri, String[] projection, String sortOrder) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Cursor queryObtemProximoNaoEscolhido(Uri uri, String[] projection, String sortOrder) {
        String sql = "select " + ProdutoClienteContract.camposOrdenados() + " from " + ProdutoClienteContract.TABLE_NAME +
                " where " + ProdutoClienteContract.COLUNA_ID_PRODUTO_CLIENTE +
                " not in (select " + InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA + " from " +
                InteresseProdutoContract.TABLE_NAME + ")";
        return this.queryRaw(sql);
    }
}
