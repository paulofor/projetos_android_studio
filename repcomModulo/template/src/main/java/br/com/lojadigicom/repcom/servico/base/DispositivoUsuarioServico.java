package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.DispositivoUsuarioContract;
import br.com.lojadigicom.repcom.modelo.DispositivoUsuario;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class DispositivoUsuarioServico {



 	public final void insereAtualiza(DispositivoUsuario item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(DispositivoUsuario item, Context ctx) {
        Uri uriInsert = DispositivoUsuarioContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(DispositivoUsuario item, Context ctx) {
        Uri uriUpdate = DispositivoUsuarioContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}