package br.com.lojadigicom.coletorprecocliente.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.coletorprecocliente.data.contract.PalavraChavePesquisaContract;
import br.com.lojadigicom.coletorprecocliente.modelo.PalavraChavePesquisa;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public abstract class PalavraChavePesquisaServico {



 	public final void insereAtualiza(PalavraChavePesquisa item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(PalavraChavePesquisa item, Context ctx) {
        Uri uriInsert = PalavraChavePesquisaContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(PalavraChavePesquisa item, Context ctx) {
        Uri uriUpdate = PalavraChavePesquisaContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}