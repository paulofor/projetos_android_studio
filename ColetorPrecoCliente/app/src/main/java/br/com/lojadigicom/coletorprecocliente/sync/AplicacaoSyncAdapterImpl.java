package br.com.lojadigicom.coletorprecocliente.sync;

import android.accounts.Account;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

/**
 * Created by Paulo on 31/10/2015.
 */
public class AplicacaoSyncAdapterImpl extends  AplicacaoSyncAdapter{
    public AplicacaoSyncAdapterImpl(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Context ctx = getContext();
        Sincronizador.executa(ctx);
    }


}
