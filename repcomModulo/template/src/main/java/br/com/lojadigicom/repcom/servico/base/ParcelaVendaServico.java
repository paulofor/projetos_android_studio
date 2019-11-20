package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.ParcelaVendaContract;
import br.com.lojadigicom.repcom.modelo.ParcelaVenda;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class ParcelaVendaServico {



 	public final void insereAtualiza(ParcelaVenda item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ParcelaVenda item, Context ctx) {
        Uri uriInsert = ParcelaVendaContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ParcelaVenda item, Context ctx) {
        Uri uriUpdate = ParcelaVendaContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}