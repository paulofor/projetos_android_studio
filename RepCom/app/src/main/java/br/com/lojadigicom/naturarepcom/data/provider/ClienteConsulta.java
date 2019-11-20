package br.com.lojadigicom.naturarepcom.data.provider;

import android.database.Cursor;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.provider.ClienteProvider;

/**
 * Created by Paulo on 31/03/2016.
 */
public class ClienteConsulta extends ClienteProvider {
    @Override
    protected Cursor queryObtemListaAgendaTel(Uri uri, String[] projection, String sortOrder) {
        return null;
    }

    @Override
    protected Cursor querySincronizaAgendaTel(Uri uri, String[] projection, String sortOrder) {
        return null;
    }

    @Override
    protected Cursor queryObtemPorIdAgendaTel(Uri uri, String[] projection, String sortOrder) {
        return null;
    }

    @Override
    protected Cursor queryPreencheDerivadaAgendaTel(Uri uri, String[] projection, String sortOrder) {
        return null;
    }

    @Override
    protected Cursor queryDesativaPorId(Uri uri, String[] projection, String sortOrder) {
        return null;
    }

    @Override
    protected Cursor queryListaAtivos(Uri uri, String[] projection, String sortOrder) {
        return null;
    }

    @Override
    protected Cursor queryPesquisaTrechoNome(Uri uri, String[] projection, String sortOrder) {
        return null;
    }
}
