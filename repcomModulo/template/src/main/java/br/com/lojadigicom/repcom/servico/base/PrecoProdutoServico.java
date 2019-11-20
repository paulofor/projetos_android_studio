package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.PrecoProdutoContract;
import br.com.lojadigicom.repcom.modelo.PrecoProduto;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class PrecoProdutoServico {



 	public final void insereAtualiza(PrecoProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(PrecoProduto item, Context ctx) {
        Uri uriInsert = PrecoProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(PrecoProduto item, Context ctx) {
        Uri uriUpdate = PrecoProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}