package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.CategoriaProdutoProdutoContract;
import br.com.lojadigicom.repcom.modelo.CategoriaProdutoProduto;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class CategoriaProdutoProdutoServico {



 	public final void insereAtualiza(CategoriaProdutoProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(CategoriaProdutoProduto item, Context ctx) {
        Uri uriInsert = CategoriaProdutoProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(CategoriaProdutoProduto item, Context ctx) {
        Uri uriUpdate = CategoriaProdutoProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}