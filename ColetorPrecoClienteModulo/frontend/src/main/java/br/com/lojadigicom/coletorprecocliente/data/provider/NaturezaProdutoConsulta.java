
package  br.com.lojadigicom.coletorprecocliente.data.provider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;

public final class NaturezaProdutoConsulta extends NaturezaProdutoProvider{

    @Override
    protected void notificaOutrasUri(ContentResolver resolver) {

    }


    @Override
    protected String queryListaAtivo(String param) {
        String sql = "select " + NaturezaProdutoContract.camposOrdenados() +
                " from " + NaturezaProdutoContract.TABLE_NAME +
                " where " + NaturezaProdutoContract.COLUNA_ATIVO;
        return sql;
    }
}