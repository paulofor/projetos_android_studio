package br.com.lojadigicom.treinoacademia.servico.base;


import android.content.Context;
import android.net.Uri;

import br.com.lojadigicom.treinoacademia.data.contract.ItemSerieContract;
import br.com.lojadigicom.treinoacademia.modelo.ItemSerie;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public abstract class ItemSerieServico {



 	public final void insereAtualiza(ItemSerie item, Context context) {
        if (item.getIdObj()==0) {
        	DCLog.d(DCLog.TRACE_CRUD,this,"insercao");
            insere(item,context);
        } else {
        	DCLog.d(DCLog.TRACE_CRUD,this,"alteracao");
            atualiza(item,context);
        }
    }
    public final void insere(ItemSerie item, Context ctx) {
        Uri uriInsert = ItemSerieContract.buildInsereSinc();
        ctx.getContentResolver().insert(uriInsert, item.getContentValues());
    }
    public final void atualiza(ItemSerie item, Context ctx) {
        Uri uriUpdate = ItemSerieContract.buildAtualizaSinc();
        ctx.getContentResolver().update(uriUpdate,item.getContentValues(),null,null);
    }
}