package br.com.lojadigicom.precomed.framework.sincronismo;

import android.content.Context;
import android.os.AsyncTask;

public abstract class SincronismoBaseTask extends AsyncTask<Context, Void, Void> {

    public interface CallbackSincronismo {
        public void onConcluido();
    }

    private CallbackSincronismo callbackSincronismo = null;

    public void setCallback(CallbackSincronismo callback) {
        callbackSincronismo = callback;
    }
    
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callbackSincronismo.onConcluido();
    }
}