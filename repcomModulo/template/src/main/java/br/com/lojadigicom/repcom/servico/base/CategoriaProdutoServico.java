package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.CategoriaProdutoContract;
import br.com.lojadigicom.repcom.modelo.CategoriaProduto;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class CategoriaProdutoServico {



 	public final void insereAtualiza(CategoriaProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(CategoriaProduto item, Context ctx) {
        Uri uriInsert = CategoriaProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(CategoriaProduto item, Context ctx) {
        Uri uriUpdate = CategoriaProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}