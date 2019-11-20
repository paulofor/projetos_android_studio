package br.com.lojadigicom.coletorprecocliente.servico.sync;

import android.os.AsyncTask;

import br.com.lojadigicom.coletorprecocliente.servico.ParamServico;
import br.com.lojadigicom.coletorprecocliente.servico.UsuarioPesquisaServicoApp;

/**
 * Created by Paulo on 24/02/16.
 */
public class UsuarioPesquisaAsyncTask extends AsyncTask<ParamServico, Void, Void> {

    @Override
    protected Void doInBackground(ParamServico... params) {
        ParamServico param = params[0];
        UsuarioPesquisaServicoApp srv = (UsuarioPesquisaServicoApp) param.getServico();
        srv.setFiltro(param.getFiltro());
        switch (param.getIdOperacao()) {
            case UsuarioPesquisaServicoApp.OP_EXCLUI_ITEM :
            {
                srv.ExcluiItem(param.getContext());
                break;
            }
            case UsuarioPesquisaServicoApp.OP_INCLUI_ITEM :
            {
                srv.IncluiItem(param.getContext());
                break;
            }
        }
        return null;
    }
}
