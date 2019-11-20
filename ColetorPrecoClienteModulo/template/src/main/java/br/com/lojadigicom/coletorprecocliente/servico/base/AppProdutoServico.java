package br.com.lojadigicom.coletorprecocliente.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.AppProdutoContract;
import br.com.lojadigicom.coletorprecocliente.modelo.AppProduto;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public abstract class AppProdutoServico {



 	public final void insereAtualiza(AppProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(AppProduto item, Context ctx) {
        Uri uriInsert = AppProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(AppProduto item, Context ctx) {
        Uri uriUpdate = AppProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}