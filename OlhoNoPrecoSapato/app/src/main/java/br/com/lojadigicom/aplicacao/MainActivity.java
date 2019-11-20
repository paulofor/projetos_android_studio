package br.com.lojadigicom.aplicacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import br.com.lojadigicom.coletorprecocliente.app.ListaEsperaActivity;
import br.com.lojadigicom.coletorprecocliente.app.ListaMonitoramentoActivity;
import br.com.lojadigicom.coletorprecocliente.app.VitrineProdutoEscolhaActivity;
import br.com.lojadigicom.coletorprecocliente.app.AdmAssinaturaActivity;
import br.com.lojadigicom.coletorprecocliente.data.helper.AplicacaoDbHelper;
import br.com.lojadigicom.coletorprecocliente.faturamento.BillingExemploActivity;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCFirebaseInstanceIDService;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCFirebaseMessagingService;
import br.com.lojadigicom.coletorprecocliente.framework.fcm.DCNotificacaoParam;
import br.com.lojadigicom.coletorprecocliente.framework.tela.DCAplicacao;
import br.com.lojadigicom.coletorprecocliente.sync.Sincronizador;
import br.com.lojadigicom.olhonoprecosapato.R;
import br.com.lojadigicom.tela.MainActivityBaseGcm;

public class MainActivity extends VitrineProdutoEscolhaActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void complementaOnCreate() {
        super.complementaOnCreate();
        this.existeBack = false;
        this.titulo = "Olho no Preço";
        //if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            //Intent intent = new Intent(this, getRegistrationIntentService());
            //startService(intent);

        //}

    }

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

    private void  inicializaSincronizador() {
        Sincronizador sinc = new Sincronizador((DCAplicacao) this.getApplication());
        DCFirebaseMessagingService.setSincronizador(sinc);
        DCFirebaseInstanceIDService.setSincronizador(sinc);
        AplicacaoDbHelper.setSincronizador(sinc);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuEspera) {
            Intent mIntent = new Intent(this, ListaEsperaActivity.class);
            startActivity(mIntent);
            return true;
        }
        if (id == R.id.menuMonitora) {
            Intent mIntent = new Intent(this, ListaMonitoramentoActivity.class);
            startActivity(mIntent);
            return true;
        }
        if (id == R.id.menuAssinatura) {
            Intent mIntent = new Intent(this, AdmAssinaturaActivity.class);
            startActivity(mIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



   

}
