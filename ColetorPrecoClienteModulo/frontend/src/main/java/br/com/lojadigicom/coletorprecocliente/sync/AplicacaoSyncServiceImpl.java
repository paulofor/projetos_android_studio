package br.com.lojadigicom.coletorprecocliente.sync;

import android.content.Context;

/**
 * Created by Paulo on 11/11/2015.
 */
public class AplicacaoSyncServiceImpl extends AplicacaoSyncService {


    @Override
    protected AplicacaoSyncAdapter criaAdapter(Context ctx, boolean valor) {

        return new AplicacaoSyncAdapterImpl(ctx, valor);
    }
}
