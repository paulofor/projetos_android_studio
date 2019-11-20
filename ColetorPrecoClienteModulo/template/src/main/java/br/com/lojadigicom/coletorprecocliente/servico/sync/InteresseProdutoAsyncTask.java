package br.com.lojadigicom.coletorprecocliente.servico.sync;

import android.os.AsyncTask;

import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.servico.InteresseProdutoServicoApp;
import br.com.lojadigicom.coletorprecocliente.servico.ParamServico;
import br.com.lojadigicom.coletorprecocliente.servico.UsuarioPesquisaServicoApp;

/**
 * Created by Paulo on 23/04/2016.
 */
public class InteresseProdutoAsyncTask extends AsyncTask<ParamServico, Void, Void> {
    @Override
    protected Void doInBackground(ParamServico... params) {
        DCLog.d(DCLog.SERVICO_DATABASE,this,"Servico Inclui/Exclui chamado");
        ParamServico param = params[0];
        InteresseProdutoServicoApp srv = (InteresseProdutoServicoApp) param.getServico();
        srv.setFiltro(param.getFiltro());
        switch (param.getIdOperacao()) {
            case UsuarioPesquisaServicoApp.OP_EXCLUI_ITEM :
            {
                DCLog.d(DCLog.SERVICO_DATABASE,this,"Operação de exclusao");
                srv.ExcluiItem(param.getContext());
                break;
            }
            case UsuarioPesquisaServicoApp.OP_INCLUI_ITEM :
            {
                DCLog.d(DCLog.SERVICO_DATABASE,this,"Operação de inclusao");
                srv.IncluiItem(param.getContext());
                break;
            }
        }
        return null;
    }
}
