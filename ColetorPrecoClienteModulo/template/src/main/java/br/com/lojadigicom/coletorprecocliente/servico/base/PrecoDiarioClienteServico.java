package br.com.lojadigicom.coletorprecocliente.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.PrecoDiarioClienteContract;
import br.com.lojadigicom.coletorprecocliente.modelo.PrecoDiarioCliente;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public abstract class PrecoDiarioClienteServico {



 	public final void insereAtualiza(PrecoDiarioCliente item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(PrecoDiarioCliente item, Context ctx) {
        Uri uriInsert = PrecoDiarioClienteContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(PrecoDiarioCliente item, Context ctx) {
        Uri uriUpdate = PrecoDiarioClienteContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}