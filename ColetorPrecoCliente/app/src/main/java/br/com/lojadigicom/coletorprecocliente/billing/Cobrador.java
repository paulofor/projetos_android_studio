package br.com.lojadigicom.coletorprecocliente.billing;

import android.app.Activity;
import android.content.IntentFilter;
import android.util.Log;

import br.com.lojadigicom.coletorprecocliente.util.IabBroadcastReceiver;
import br.com.lojadigicom.coletorprecocliente.util.IabHelper;

/**
 * Created by Paulo on 02/04/2016.
 */
public class Cobrador {

    // Debug tag, for logging
    static final String TAG = "Cobrador";

    static final String SKU_EXEMPLO_PRODUTO = "produto_teste_01";
    static final String SKU_EXEMPLO_ASSINATURA = "categoria_simples";

    // The helper object
    IabHelper mHelper;

    // Provides purchase notification while this app is running
    IabBroadcastReceiver mBroadcastReceiver;

    private void inicializaBilling(Activity cliente) {


    }
}
