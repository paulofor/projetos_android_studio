package br.com.lojadigicom.capitalexterno.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.capitalexterno.data.contract.CustoMensalContract;
import br.com.lojadigicom.capitalexterno.modelo.CustoMensal;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class CustoMensalServico {



 	public final void insereAtualiza(CustoMensal item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(CustoMensal item, Context ctx) {
        Uri uriInsert = CustoMensalContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(CustoMensal item, Context ctx) {
        Uri uriUpdate = CustoMensalContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}