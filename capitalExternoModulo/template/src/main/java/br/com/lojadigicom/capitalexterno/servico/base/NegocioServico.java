package br.com.lojadigicom.capitalexterno.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.capitalexterno.data.contract.NegocioContract;
import br.com.lojadigicom.capitalexterno.modelo.Negocio;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class NegocioServico {



 	public final void insereAtualiza(Negocio item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(Negocio item, Context ctx) {
        Uri uriInsert = NegocioContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(Negocio item, Context ctx) {
        Uri uriUpdate = NegocioContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}