package br.com.lojadigicom.naturarepcom.data.provider;

import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.provider.MesAnoProvider;

/**
 * Created by Paulo on 31/03/2016.
 */
public class MesAnoConsulta extends MesAnoProvider {
    @Override
    protected Cursor queryObtemMesCorrente(Uri uri, String[] projection, String sortOrder) {
        return null;
    }
}
