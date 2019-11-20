package br.com.lojadigicom.coletorprecocliente.framework.data;


import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import br.com.lojadigicom.coletorprecocliente.framework.dao.MontadorDaoI;

/**
 * Created by Paulo on 14/11/2015.
 */
public class DCCursorLoader extends CursorLoader {


    private MontadorDaoI montador = null;
    private int codigoLoader = 0;

    public DCCursorLoader(Context context) {
        super(context);
    }

    public DCCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder,int codigoLoader) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
        this.codigoLoader = codigoLoader;
    }

    public DCCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }

    public DCCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, MontadorDaoI montador) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
        this.montador = montador;
    }

    public MontadorDaoI getMontador() {
        return montador;
    }

    public int getCodigoLoader() {
        return codigoLoader;
    }
}
