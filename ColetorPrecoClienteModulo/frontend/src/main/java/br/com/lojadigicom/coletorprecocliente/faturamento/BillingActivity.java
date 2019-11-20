package br.com.lojadigicom.coletorprecocliente.faturamento;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.vending.billing.IInAppBillingService;

/**
 * Created by Paulo on 04/02/2017.
 */

public abstract class BillingActivity extends AppCompatActivity {

    protected String tag = "in_app_billing_ex";

    protected IInAppBillingService mService;

    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(tag, "onServiceDisconnected");
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(tag, "onServiceConnected");
            mService = IInAppBillingService.Stub.asInterface(service);
        }
    };

    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResource());
        Log.i(tag, "onCreate.mServiceConn= " + mServiceConn);

        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        final boolean blnBind = bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
        Log.i(tag, "bindService - return " + String.valueOf(blnBind));
        //executaTarefasCompras(mService);

        Log.i(tag, "onCreate.mService= " + mService);

        complementaCreate(savedInstanceState);
    }

    protected abstract void complementaCreate(Bundle savedInstanceState);

    protected abstract void executaTarefasCompras(IInAppBillingService mService);

    protected abstract int getResource();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            unbindService(mServiceConn);
        }
    }


}
