package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.ProdutoVendaContract;
import br.com.lojadigicom.repcom.modelo.ProdutoVenda;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class ProdutoVendaServico {



 	public final void insereAtualiza(ProdutoVenda item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ProdutoVenda item, Context ctx) {
        Uri uriInsert = ProdutoVendaContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ProdutoVenda item, Context ctx) {
        Uri uriUpdate = ProdutoVendaContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}