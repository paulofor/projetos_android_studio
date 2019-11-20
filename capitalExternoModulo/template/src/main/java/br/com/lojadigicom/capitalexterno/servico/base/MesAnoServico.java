package br.com.lojadigicom.capitalexterno.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.capitalexterno.data.contract.MesAnoContract;
import br.com.lojadigicom.capitalexterno.modelo.MesAno;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class MesAnoServico {



 	public final void insereAtualiza(MesAno item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(MesAno item, Context ctx) {
        Uri uriInsert = MesAnoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(MesAno item, Context ctx) {
        Uri uriUpdate = MesAnoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}