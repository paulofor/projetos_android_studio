package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.ErroExceptionContract;
import br.com.lojadigicom.repcom.modelo.ErroException;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class ErroExceptionServico {



 	public final void insereAtualiza(ErroException item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ErroException item, Context ctx) {
        Uri uriInsert = ErroExceptionContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ErroException item, Context ctx) {
        Uri uriUpdate = ErroExceptionContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}