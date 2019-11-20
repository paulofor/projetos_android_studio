package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.VendaContract;
import br.com.lojadigicom.repcom.modelo.Venda;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class VendaServico {



 	public final void insereAtualiza(Venda item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(Venda item, Context ctx) {
        Uri uriInsert = VendaContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(Venda item, Context ctx) {
        Uri uriUpdate = VendaContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}