package br.com.lojadigicom.coletorprecocliente.servico.sync;

import android.os.AsyncTask;

import br.com.lojadigicom.coletorprecocliente.servico.InteresseProdutoServico;
import br.com.lojadigicom.coletorprecocliente.servico.ParamServico;
import br.com.lojadigicom.coletorprecocliente.servico.UsuarioPesquisaServico;

/**
 * Created by Paulo on 23/04/2016.
 */
public class InteresseProdutoAsyncTask extends AsyncTask<ParamServico, Void, Void> {
    @Override
    protected Void doInBackground(ParamServico... params) {
        ParamServico param = params[0];
        InteresseProdutoServico srv = (InteresseProdutoServico) param.getServico();
        srv.setFiltro(param.getFiltro());
        switch (param.getIdOperacao()) {
            case UsuarioPesquisaServico.OP_EXCLUI_ITEM :
            {
                srv.ExcluiItem(param.getContext());
                break;
            }
            case UsuarioPesquisaServico.OP_INCLUI_ITEM :
            {
                srv.IncluiItem(param.getContext());
                break;
            }
        }
        return null;
    }
}
