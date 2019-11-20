package br.com.lojadigicom.coletorprecocliente.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeInteresseClienteContract;
import br.com.lojadigicom.coletorprecocliente.modelo.OportunidadeInteresseCliente;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public abstract class OportunidadeInteresseClienteServico {



 	public final void insereAtualiza(OportunidadeInteresseCliente item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(OportunidadeInteresseCliente item, Context ctx) {
        Uri uriInsert = OportunidadeInteresseClienteContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(OportunidadeInteresseCliente item, Context ctx) {
        Uri uriUpdate = OportunidadeInteresseClienteContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}