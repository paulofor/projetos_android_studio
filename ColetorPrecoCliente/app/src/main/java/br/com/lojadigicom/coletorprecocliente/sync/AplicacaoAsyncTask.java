package br.com.lojadigicom.coletorprecocliente.sync;

import android.content.Context;
import android.os.AsyncTask;

import br.com.lojadigicom.coletorprecocliente.framework.util.FileUtil;

/**
 * Created by Paulo on 19/11/15.
 */
public class AplicacaoAsyncTask extends AsyncTask<Context, Void, Void> {




    @Override
    protected Void doInBackground(Context... params) {
        Sincronizador.executa(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        FileUtil.copiaArquivo();
    }
}
