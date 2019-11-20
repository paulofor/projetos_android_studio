
package  br.com.lojadigicom.coletorprecocliente.data.provider;

import android.database.Cursor;
import android.net.Uri;

public final class NaturezaProdutoConsulta extends NaturezaProdutoProvider{


    @Override
    protected Cursor queryListaAtivo(Uri uri, String[] projection, String sortOrder) {
        String selection = "ativo ";
        return sQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                selection,
                null,
                null,
                null,
                sortOrder
        );
    }
}