package br.com.lojadigicom.coletorprecocliente.sync;

import android.content.Context;

import br.com.lojadigicom.coletorprecocliente.data.contract.AplicacaoContract;
import br.com.lojadigicom.coletorprecocliente.framework.sincronismo.SincronismoBaseTask;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.remoto.DispositivoUsuarioSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.InteresseProdutoSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.NaturezaProdutoSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.OportunidadeDiaSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.ProdutoClienteSincronismo;
import br.com.lojadigicom.coletorprecocliente.remoto.UsuarioSincronismo;

/**
 * Created by Paulo on 13/07/2016.
 */
public class SincronizacaoCargaTask  extends SincronismoBaseTask {
    @Override
    protected Void doInBackground(Context... params) {
        Context ctx = params[0];
        InteresseProdutoSincronismo interesseProdutoSincronismo = new InteresseProdutoSincronismo();
        interesseProdutoSincronismo.sincroniza(ctx,true, true, true);

//        InteresseProdutoSincronismo interesseProdutoSincronismo = new InteresseProdutoSincronismo();
//        interesseProdutoSincronismo.sincroniza(ctx,true, true, Sincronizador.getCodigoAplicacao());
//        ProdutoClienteSincronismo produtoClienteSincronismo = new ProdutoClienteSincronismo();
//        produtoClienteSincronismo.sincroniza(ctx,true, true, Sincronizador.getCodigoAplicacao());
//        OportunidadeDiaSincronismo oportunidadeDiaSincronismo = new OportunidadeDiaSincronismo();
//        oportunidadeDiaSincronismo.sincroniza(ctx,true, true, Sincronizador.getCodigoAplicacao());
//         return null;
        return null;
    }
}
