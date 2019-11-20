package br.com.lojadigicom.treinoacademia.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.treinoacademia.data.contract.CargaPlanejadaContract;
import br.com.lojadigicom.treinoacademia.modelo.CargaPlanejada;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public abstract class CargaPlanejadaServico {



 	public final void insereAtualiza(CargaPlanejada item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(CargaPlanejada item, Context ctx) {
        Uri uriInsert = CargaPlanejadaContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(CargaPlanejada item, Context ctx) {
        Uri uriUpdate = CargaPlanejadaContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}