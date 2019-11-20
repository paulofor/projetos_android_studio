package br.com.lojadigicom.sync;

import android.content.Context;
import android.os.AsyncTask;

import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.framework.util.FileUtil;
import br.com.lojadigicom.coletorprecocliente.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.UsuarioSincronismo;
import br.com.lojadigicom.coletorprecocliente.sync.Sincronizador;

/**
 * Created by Paulo on 08/10/2016.
 */

public class AsyncTaskUtil extends AsyncTask<Context, Void, Void> {



    @Override
    protected Void doInBackground(Context... params) {
        sincronizaUsuarioDispositivo(params[0]);
        return null;
    }

    /*
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        FileUtil.copiaArquivo();
    }
    */

    private void sincronizaUsuarioDispositivo(Context ctx) {
        // Primeiro atualizar dispositivo para criar automaticamente o usuario.
        DispositivoUsuarioSincronismo dispositivoUsuario = new DispositivoUsuarioSincronismo();
        dispositivoUsuario.sincroniza(ctx,true);
        UsuarioSincronismo usuarioSincronismo = new UsuarioSincronismo();
        usuarioSincronismo.sincroniza(ctx,true, false, true);
    }
}
