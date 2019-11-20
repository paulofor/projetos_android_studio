package br.com.lojadigicom.capitalexterno.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.capitalexterno.data.contract.PrevisaoVendaContract;
import br.com.lojadigicom.capitalexterno.modelo.PrevisaoVenda;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class PrevisaoVendaServico {



 	public final void insereAtualiza(PrevisaoVenda item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(PrevisaoVenda item, Context ctx) {
        Uri uriInsert = PrevisaoVendaContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(PrevisaoVenda item, Context ctx) {
        Uri uriUpdate = PrevisaoVendaContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}