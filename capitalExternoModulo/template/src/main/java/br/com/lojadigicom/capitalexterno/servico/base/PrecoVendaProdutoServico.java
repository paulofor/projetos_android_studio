package br.com.lojadigicom.capitalexterno.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.capitalexterno.data.contract.PrecoVendaProdutoContract;
import br.com.lojadigicom.capitalexterno.modelo.PrecoVendaProduto;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class PrecoVendaProdutoServico {



 	public final void insereAtualiza(PrecoVendaProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(PrecoVendaProduto item, Context ctx) {
        Uri uriInsert = PrecoVendaProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(PrecoVendaProduto item, Context ctx) {
        Uri uriUpdate = PrecoVendaProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}