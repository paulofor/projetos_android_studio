package br.com.lojadigicom.capitalexterno.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.capitalexterno.data.contract.UsuarioContract;
import br.com.lojadigicom.capitalexterno.modelo.Usuario;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class UsuarioServico {



 	public final void insereAtualiza(Usuario item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(Usuario item, Context ctx) {
        Uri uriInsert = UsuarioContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(Usuario item, Context ctx) {
        Uri uriUpdate = UsuarioContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}