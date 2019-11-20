package br.com.lojadigicom.treinoacademia.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.treinoacademia.data.contract.ExecucaoItemSerieContract;
import br.com.lojadigicom.treinoacademia.modelo.ExecucaoItemSerie;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public abstract class ExecucaoItemSerieServico {



 	public final void insereAtualiza(ExecucaoItemSerie item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ExecucaoItemSerie item, Context ctx) {
        Uri uriInsert = ExecucaoItemSerieContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ExecucaoItemSerie item, Context ctx) {
        Uri uriUpdate = ExecucaoItemSerieContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}