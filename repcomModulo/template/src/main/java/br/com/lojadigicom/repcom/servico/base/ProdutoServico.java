package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.ProdutoContract;
import br.com.lojadigicom.repcom.modelo.Produto;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class ProdutoServico {



 	public final void insereAtualiza(Produto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(Produto item, Context ctx) {
        Uri uriInsert = ProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(Produto item, Context ctx) {
        Uri uriUpdate = ProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}