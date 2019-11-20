    package br.com.lojadigicom.coletorprecocliente.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.vending.billing.IInAppBillingService;

import java.util.ArrayList;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.faturamento.BillingActivity;
import br.com.lojadigicom.coletorprecocliente.faturamento.BillingBaseActivity;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;

    public class AdmAssinaturaActivity extends BillingBaseActivity implements LoaderManager.LoaderCallbacks<Cursor>{

     private final int LIMITE_ESPERA_SEM_ASSINATURA = 20;
     private final int LIMITE_MONITORA_SEM_ASSINATURA = 10;

     private final int LIMITE_ESPERA_ASSINATURA1 = 100;
     private final int LIMITE_MONITORA_ASSINATURA1 = 50;

     private static final int DETAIL_LOADER = 99998;

     private int quantidadeMonitorada = 0;
     private int quantidadeEspera = 0;


    private TextView txtQtdeMonitora = null;
    private TextView txtQtdeEspera = null;
    private TextView txtLimiteMonitora = null;
    private TextView txtLimiteEspera = null;
    private TextView txtLimiteNovoMonitora = null;
    private TextView txtLimiteNovoEspera = null;
    private Button btnAssinatura = null;



    @Override
    protected void complementaCreate(Bundle savedInstanceState) {
        getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);
        txtQtdeMonitora = (TextView) this.findViewById(R.id.txtQtdeMonitora);
        txtQtdeEspera = (TextView) this.findViewById(R.id.txtQtdeEspera);
        txtLimiteMonitora = (TextView) this.findViewById(R.id.txtLimiteMonitora);
        txtLimiteEspera = (TextView) this.findViewById(R.id.txtLimiteEspera);
        txtLimiteNovoMonitora = (TextView) this.findViewById(R.id.txtLimiteNovoMonitora);
        txtLimiteNovoEspera = (TextView) this.findViewById(R.id.txtLimiteNovoEspera);
        btnAssinatura = (Button) this.findViewById(R.id.btnAssinatura);
        btnAssinatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               comprarAssinatura();
            }
        });
        updateUi();
    }

     private void possuiAssinatura1() {
         txtLimiteMonitora.setText("" + LIMITE_MONITORA_ASSINATURA1);
         txtLimiteEspera.setText("" + LIMITE_ESPERA_ASSINATURA1);
     }
     private void naoPossuiAssinatura1() {
         txtLimiteMonitora.setText("" + LIMITE_MONITORA_SEM_ASSINATURA);
         txtLimiteEspera.setText("" + LIMITE_ESPERA_SEM_ASSINATURA);
         txtLimiteNovoMonitora.setText("" + LIMITE_MONITORA_ASSINATURA1);
         txtLimiteNovoEspera.setText("" + LIMITE_ESPERA_ASSINATURA1);
     }



     @Override
     protected void updateUi() {
         Log.d(TAG, "Passou no update");
         if (this.getAssinatura1()) {
             possuiAssinatura1();
         } else {
             naoPossuiAssinatura1();
         }
     }


    @Override
    protected int getResource() {
        return R.layout.activity_adm_assinatura;
    }


     @Override
     public Loader<Cursor> onCreateLoader(int id, Bundle args) {
         Uri uri = InteresseProdutoContract.buildQuantidadeMonitorado();
         return new DCCursorLoader(this,uri,null,null,null,null);
     }

     @Override
     public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
         quantidadeMonitorada = 0;
         quantidadeEspera = 0;
         if (data.moveToFirst()) {
             quantidadeMonitorada = data.getInt(data.getColumnIndex("qtde_monitora"));
             quantidadeEspera = data.getInt(data.getColumnIndex("qtde_espera"));
         }
         txtQtdeMonitora.setText("" + quantidadeMonitorada);
         txtQtdeEspera.setText("" + quantidadeEspera);
     }

     @Override
     public void onLoaderReset(Loader<Cursor> loader) {

     }
 }
