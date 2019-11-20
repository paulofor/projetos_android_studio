package br.com.lojadigicom.coletorprecocliente.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.ProdutoClienteContract;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public abstract class ProdutoClienteServico {



 	public final void insereAtualiza(ProdutoCliente item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ProdutoCliente item, Context ctx) {
        Uri uriInsert = ProdutoClienteContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ProdutoCliente item, Context ctx) {
        Uri uriUpdate = ProdutoClienteContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}