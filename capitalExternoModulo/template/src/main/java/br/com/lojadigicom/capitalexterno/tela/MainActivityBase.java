
package br.com.lojadigicom.capitalexterno.tela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.framework.tela.ResourceObj;
import br.com.lojadigicom.capitalexterno.template.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by Paulo on 04/01/16.
 */
public abstract class MainActivityBase extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private ResourceObj resourceObj = getActivityMainResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DCLog.d(DCLog.DISPLAY,this,"(" + resourceObj.getNome() + ")");
        setContentView(resourceObj.getCodigo());
        
        Toolbar toolbar = (Toolbar) findViewById(getToolbarId());
        setSupportActionBar(toolbar);


        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, getRegistrationIntentService());
            startService(intent);
        }
        sincronizacaoManual();
    }

    protected void sincronizacaoManual() {

    }

    protected abstract Class<?> getRegistrationIntentService();

    //protected abstract int getActivityMainId();
    protected abstract ResourceObj getActivityMainResource();
    protected abstract int getToolbarId();
   


    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }


}
