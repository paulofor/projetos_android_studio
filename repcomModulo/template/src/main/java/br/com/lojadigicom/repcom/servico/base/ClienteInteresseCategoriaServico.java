package br.com.lojadigicom.repcom.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.repcom.data.contract.ClienteInteresseCategoriaContract;
import br.com.lojadigicom.repcom.modelo.ClienteInteresseCategoria;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class ClienteInteresseCategoriaServico {



 	public final void insereAtualiza(ClienteInteresseCategoria item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ClienteInteresseCategoria item, Context ctx) {
        Uri uriInsert = ClienteInteresseCategoriaContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ClienteInteresseCategoria item, Context ctx) {
        Uri uriUpdate = ClienteInteresseCategoriaContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}