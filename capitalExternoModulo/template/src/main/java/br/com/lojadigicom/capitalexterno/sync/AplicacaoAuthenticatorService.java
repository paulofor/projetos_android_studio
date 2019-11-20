
package  br.com.lojadigicom.capitalexterno.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AplicacaoAuthenticatorService extends Service{
	// Instance field that stores the authenticator object
    private AplicacaoAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new AplicacaoAuthenticator(this);
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}