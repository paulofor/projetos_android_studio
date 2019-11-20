package br.com.lojadigicom.capitalexterno.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.capitalexterno.data.contract.ItemCustoProdutoContract;
import br.com.lojadigicom.capitalexterno.modelo.ItemCustoProduto;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class ItemCustoProdutoServico {



 	public final void insereAtualiza(ItemCustoProduto item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ItemCustoProduto item, Context ctx) {
        Uri uriInsert = ItemCustoProdutoContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ItemCustoProduto item, Context ctx) {
        Uri uriUpdate = ItemCustoProdutoContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}