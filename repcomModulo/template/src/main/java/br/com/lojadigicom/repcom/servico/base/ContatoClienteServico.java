package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.ContatoClienteContract;
import br.com.lojadigicom.repcom.modelo.ContatoCliente;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class ContatoClienteServico {



 	public final void insereAtualiza(ContatoCliente item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ContatoCliente item, Context ctx) {
        Uri uriInsert = ContatoClienteContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ContatoCliente item, Context ctx) {
        Uri uriUpdate = ContatoClienteContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}