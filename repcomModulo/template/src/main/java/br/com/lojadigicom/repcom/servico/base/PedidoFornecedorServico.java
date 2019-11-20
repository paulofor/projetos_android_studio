package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.PedidoFornecedorContract;
import br.com.lojadigicom.repcom.modelo.PedidoFornecedor;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class PedidoFornecedorServico {



 	public final void insereAtualiza(PedidoFornecedor item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(PedidoFornecedor item, Context ctx) {
        Uri uriInsert = PedidoFornecedorContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(PedidoFornecedor item, Context ctx) {
        Uri uriUpdate = PedidoFornecedorContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}