package br.com.lojadigicom.coletorprecocliente.sync;

import android.database.ContentObserver;
import android.os.Handler;

import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListAdapterBase;

/**
 * Created by Paulo on 07/03/2016.
 */
public class Observador extends ContentObserver{
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */

    private NaturezaProdutoListAdapterBase mAdapter;


    public Observador(Handler handler) {
        super(handler);
    }
    public void setAdapter(NaturezaProdutoListAdapterBase valor) {
        mAdapter = valor;
    }
}
