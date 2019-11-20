package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.ProdutoPedidoFornecedorContract;
import br.com.lojadigicom.repcom.modelo.ProdutoPedidoFornecedor;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class ProdutoPedidoFornecedorServico {



 	public final void insereAtualiza(ProdutoPedidoFornecedor item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ProdutoPedidoFornecedor item, Context ctx) {
        Uri uriInsert = ProdutoPedidoFornecedorContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ProdutoPedidoFornecedor item, Context ctx) {
        Uri uriUpdate = ProdutoPedidoFornecedorContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}