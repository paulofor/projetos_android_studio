package br.com.lojadigicom.naturarepcom.data.provider;

import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.provider.VendaProvider;

/**
 * Created by Paulo on 31/03/2016.
 */
public class VendaConsulta extends VendaProvider {
    @Override
    protected Cursor queryAtualizaTotal(Uri uri, String[] projection, String sortOrder) {
        return null;
    }

    @Override
    protected Cursor queryCriaParcelamento(Uri uri, String[] projection, String sortOrder) {
        return null;
    }
}
