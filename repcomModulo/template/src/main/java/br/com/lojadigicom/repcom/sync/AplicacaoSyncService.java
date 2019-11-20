
package  br.com.lojadigicom.repcom.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.content.Context;

public abstract class AplicacaoSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static AplicacaoSyncAdapter sAppSyncAdapter = null;

    @Override
    public void onCreate() {
          synchronized (sSyncAdapterLock) {
            if (sAppSyncAdapter == null) {
                sAppSyncAdapter = criaAdapter(getApplicationContext(), true);
            }
        }
    }


	protected abstract AplicacaoSyncAdapter criaAdapter(Context ctx, boolean valor);
	

    @Override
    public IBinder onBind(Intent intent) {
        return sAppSyncAdapter.getSyncAdapterBinder();
    }
}