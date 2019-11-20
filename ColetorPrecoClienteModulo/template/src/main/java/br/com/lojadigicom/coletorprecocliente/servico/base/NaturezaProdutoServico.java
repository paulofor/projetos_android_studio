package br.com.lojadigicom.coletorprecocliente.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public abstract class NaturezaProdutoServico {



 	public final void insereAtualiza(NaturezaProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(NaturezaProduto item, Context ctx) {
        Uri uriInsert = NaturezaProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(NaturezaProduto item, Context ctx) {
        Uri uriUpdate = NaturezaProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}