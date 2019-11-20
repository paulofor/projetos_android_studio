package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.LinhaProdutoContract;
import br.com.lojadigicom.repcom.modelo.LinhaProduto;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class LinhaProdutoServico {



 	public final void insereAtualiza(LinhaProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(LinhaProduto item, Context ctx) {
        Uri uriInsert = LinhaProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(LinhaProduto item, Context ctx) {
        Uri uriUpdate = LinhaProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}