package br.com.lojadigicom.coletorprecocliente.servico.sync;

import android.content.Context;
import android.os.AsyncTask;

import br.com.lojadigicom.coletorprecocliente.servico.ParamServico;
import br.com.lojadigicom.coletorprecocliente.servico.UsuarioPesquisaServico;

/**
 * Created by Paulo on 24/02/16.
 */
public class UsuarioPesquisaAsyncTask extends AsyncTask<ParamServico, Void, Void> {

    @Override
    protected Void doInBackground(ParamServico... params) {
        ParamServico param = params[0];
        UsuarioPesquisaServico srv = (UsuarioPesquisaServico) param.getServico();
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
