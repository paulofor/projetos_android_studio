package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.EstoqueContract;
import br.com.lojadigicom.repcom.modelo.Estoque;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class EstoqueServico {



 	public final void insereAtualiza(Estoque item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(Estoque item, Context ctx) {
        Uri uriInsert = EstoqueContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(Estoque item, Context ctx) {
        Uri uriUpdate = EstoqueContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}