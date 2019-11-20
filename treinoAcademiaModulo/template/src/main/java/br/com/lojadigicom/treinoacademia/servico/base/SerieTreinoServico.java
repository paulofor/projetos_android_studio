package br.com.lojadigicom.treinoacademia.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.treinoacademia.data.contract.SerieTreinoContract;
import br.com.lojadigicom.treinoacademia.modelo.SerieTreino;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public abstract class SerieTreinoServico {



 	public final void insereAtualiza(SerieTreino item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(SerieTreino item, Context ctx) {
        Uri uriInsert = SerieTreinoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(SerieTreino item, Context ctx) {
        Uri uriUpdate = SerieTreinoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}