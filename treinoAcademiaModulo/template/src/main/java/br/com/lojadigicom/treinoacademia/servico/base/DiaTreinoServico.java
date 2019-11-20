package br.com.lojadigicom.treinoacademia.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.treinoacademia.data.contract.DiaTreinoContract;
import br.com.lojadigicom.treinoacademia.modelo.DiaTreino;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public abstract class DiaTreinoServico {



 	public final void insereAtualiza(DiaTreino item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(DiaTreino item, Context ctx) {
        Uri uriInsert = DiaTreinoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(DiaTreino item, Context ctx) {
        Uri uriUpdate = DiaTreinoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}