package br.com.lojadigicom.coletorprecocliente.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.CompartilhamentoProdutoContract;
import br.com.lojadigicom.coletorprecocliente.modelo.CompartilhamentoProduto;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public abstract class CompartilhamentoProdutoServico {



 	public final void insereAtualiza(CompartilhamentoProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(CompartilhamentoProduto item, Context ctx) {
        Uri uriInsert = CompartilhamentoProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(CompartilhamentoProduto item, Context ctx) {
        Uri uriUpdate = CompartilhamentoProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}