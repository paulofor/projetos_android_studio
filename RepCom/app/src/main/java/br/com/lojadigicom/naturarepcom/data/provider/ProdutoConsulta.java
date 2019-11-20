package br.com.lojadigicom.naturarepcom.data.provider;

import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.provider.ProdutoProvider;

/**
 * Created by Paulo on 31/03/2016.
 */
public class ProdutoConsulta extends ProdutoProvider {
    @Override
    protected Cursor queryListaPorIdCategoria(Uri uri, String[] projection, String sortOrder) {
        return null;
    }

    @Override
    protected Cursor queryPesquisaTrechoNome(Uri uri, String[] projection, String sortOrder) {
        return null;
    }
}
