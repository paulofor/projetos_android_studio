package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.PagamentoFornecedorContract;
import br.com.lojadigicom.repcom.modelo.PagamentoFornecedor;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class PagamentoFornecedorServico {



 	public final void insereAtualiza(PagamentoFornecedor item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(PagamentoFornecedor item, Context ctx) {
        Uri uriInsert = PagamentoFornecedorContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(PagamentoFornecedor item, Context ctx) {
        Uri uriUpdate = PagamentoFornecedorContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}