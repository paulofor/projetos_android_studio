package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.ClienteContract;
import br.com.lojadigicom.repcom.modelo.Cliente;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class ClienteServico {



 	public final void insereAtualiza(Cliente item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(Cliente item, Context ctx) {
        Uri uriInsert = ClienteContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(Cliente item, Context ctx) {
        Uri uriUpdate = ClienteContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}