package br.com.lojadigicom.capitalexterno.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.capitalexterno.data.contract.CenarioContract;
import br.com.lojadigicom.capitalexterno.modelo.Cenario;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class CenarioServico {



 	public final void insereAtualiza(Cenario item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(Cenario item, Context ctx) {
        Uri uriInsert = CenarioContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(Cenario item, Context ctx) {
        Uri uriUpdate = CenarioContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}