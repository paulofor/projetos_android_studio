package br.com.lojadigicom.treinoacademia.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.treinoacademia.data.contract.GrupoMuscularContract;
import br.com.lojadigicom.treinoacademia.modelo.GrupoMuscular;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public abstract class GrupoMuscularServico {



 	public final void insereAtualiza(GrupoMuscular item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(GrupoMuscular item, Context ctx) {
        Uri uriInsert = GrupoMuscularContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(GrupoMuscular item, Context ctx) {
        Uri uriUpdate = GrupoMuscularContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}