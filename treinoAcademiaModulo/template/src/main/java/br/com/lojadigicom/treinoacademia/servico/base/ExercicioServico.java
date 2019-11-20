package br.com.lojadigicom.treinoacademia.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.treinoacademia.data.contract.ExercicioContract;
import br.com.lojadigicom.treinoacademia.modelo.Exercicio;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public abstract class ExercicioServico {



 	public final void insereAtualiza(Exercicio item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(Exercicio item, Context ctx) {
        Uri uriInsert = ExercicioContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(Exercicio item, Context ctx) {
        Uri uriUpdate = ExercicioContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}